package com.thoughtmechanix.licenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

  @Autowired
  private LicenseRepository licenseRepository;

  @Autowired
  private OrganizationRestTemplateClient organizationRestTemplateClient;

  @Autowired
  private ServiceConfig serviceConfig;

  @HystrixCommand(commandProperties = @HystrixProperty(
      name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"))
  public List<License> getLicenseByOrg(String organizationId) {
    randomlyRunLong();
    return licenseRepository.findByOrganizationId(organizationId);
  }

  public License getLicense(String organizationId, String licenseId) {
    License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
    Organization organization = getOrganization(organizationId);
    return license
        .withOrganizationName(organization.getName())
        .withContactEmail(organization.getContactEmail())
        .withContactName(organization.getContactName())
        .withContactPhone(organization.getContactPhone())
        .withComment(serviceConfig.getExampleProperty());
  }

  public Organization getOrganization(String organizationId) {
    return organizationRestTemplateClient.getOrganization(organizationId);
  }

  private void randomlyRunLong() {
    Random random = new Random();

    int randomNum = random.nextInt(3) + 1;

    if (randomNum == 3) {
      sleep();
    }
  }

  private void sleep() {
    try {
      Thread.sleep(11000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
