package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

  @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
  public License getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
    return new License()
        .withId(licenseId)
        .withOrganizationId(organizationId)
        .withProductName("Teleco")
        .withLicenseType("Seat");
  }

  @RequestMapping(value = "/{licenseId}", method = RequestMethod.PUT)
  public String updateLicense(@PathVariable String licenseId) {
    return String.format("This is the put");
  }

  @RequestMapping(value = "/{licenseId}", method = RequestMethod.POST)
  public String saveLicense(@PathVariable String licenseId) {
    return String.format("This is the post");
  }

  @RequestMapping(value = "/{licenseId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteLicense(@PathVariable String licenseId) {
    return String.format("This is the delete");
  }
}
