package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

  @Autowired
  private LicenseRepository licenseRepository;

  @Autowired
  private ServiceConfig serviceConfig;

  @Autowired
  private OrganizationDiscoveryClient organizationDiscoveryClient;

  @Autowired
  private OrganizationRestTemplateClient organizationRestTemplateClient;

  public License getLicenses(String organizationId, String licenseId, String clientType) {
    License license =
        licenseRepository.findByOrOrganizationIdAndLicenseId(organizationId, licenseId);

    Organization organization = retrieveOrgInfo(organizationId, clientType);

    return license
        .withOrganizationName(organization.getName())
        .withContactEmail(organization.getContactEmail())
        .withContactName(organization.getContactName())
        .withContactPhone(organization.getContactPhone())
        .withComment(serviceConfig.getExampleProperty());
  }

  private Organization retrieveOrgInfo(String organizationId, String clientType) {
    Organization organization = null;

    switch (clientType) {
      case "discovery":
        System.out.println("I am using the discovery client.");
        organization = organizationDiscoveryClient.getOrganization(organizationId);
        break;
      case "rest":
        System.out.println("I am using the rest client.");
        organization = organizationRestTemplateClient.getOrganization(organizationId);
        break;
    }

    return organization;
  }
}
