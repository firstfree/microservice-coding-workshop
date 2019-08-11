package com.thoughtmechanix.lincenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.lincenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.lincenses.config.ServiceConfig;
import com.thoughtmechanix.lincenses.model.License;
import com.thoughtmechanix.lincenses.model.Organization;
import com.thoughtmechanix.lincenses.repository.LicenseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LicenseService {

  private final LicenseRepository licenseRepository;
  private final OrganizationRestTemplateClient organizationRestTemplateClient;
  private final ServiceConfig serviceConfig;

  @HystrixCommand(fallbackMethod = "buildFallbackLicenses",
      threadPoolKey = "licensesThreadPool",
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "30"),
          @HystrixProperty(name = "maxQueueSize", value = "10")
      },
      commandProperties = {
          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
          @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5"),
          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
      })
  public List<License> getLicenses(String organizationId) {
    randomlyRunLong();
    return licenseRepository.findByOrganizationId(organizationId);
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

  private List<License> buildFallbackLicenses(String organizationId) {
    List<License> fallbacks = new ArrayList<>();
    License license = new License()
        .withId("0000000-00-00000")
        .withOrganizationId(organizationId)
        .withProductName("Sorry no licensing information currently available");
    fallbacks.add(license);
    return fallbacks;
  }
}
