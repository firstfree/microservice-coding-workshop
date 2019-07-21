package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

  @Autowired
  private LicenseService licenseService;

  @GetMapping
  public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
    return licenseService.getLicensesByOrg(organizationId);
  }

  @GetMapping("/{licenseId}")
  public License getLicense(@PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId) {
    return licenseService.getLicense(organizationId, licenseId);
  }

  @PostMapping
  public void saveLicenses(@RequestBody License license) {
    licenseService.saveLicense(license);
  }

  @PutMapping("/{licenseId}")
  public String updateLicense(@PathVariable("licenseId") String licenesId) {
    return String.format("This is the Put");
  }

  @DeleteMapping("/{licenseId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteLicense(@PathVariable("licenseId") String licenseId) {
    return String.format("This is the Delete");
  }
}
