package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

  @Autowired
  private LicenseRepository licenseRepository;

  public License getLicenses(String organizationId, String licenseId, String clientType) {
    License license =
        licenseRepository.findByOrOrganizationIdAndLicenseId(organizationId, licenseId);

    Organization organization = retrieveOrgInfo(organizationId, clientType);

    return license
        .withOrganizationName(organization.getName())
        .withContactEmail(organization.getContactEmail())
        .withContactName(organization.getContactName())
        .withContactPhone(organization.getContactPhone())
        .withComment("");
  }

  private Organization retrieveOrgInfo(String organizationId, String clientType) {
    return null;
  }
}
