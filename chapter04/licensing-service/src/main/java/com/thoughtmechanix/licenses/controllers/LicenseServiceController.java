package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

  @Autowired
  private LicenseService licenseService;

  @GetMapping("/{licenseId}/{clientType}")
  public License getLicenseWithClient(@PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId,
      @PathVariable("clientType") String clientType) {
    return licenseService.getLicenses(organizationId, licenseId, clientType);
  }
}
