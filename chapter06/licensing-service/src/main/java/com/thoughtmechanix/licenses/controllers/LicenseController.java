package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseController {

  private final LicenseService licenseService;

  @GetMapping
  public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
    log.debug("LicenseController Correlation id: {}",
        UserContextHolder.getContext().getCorrelationId());
    return licenseService.getLicenses(organizationId);
  }

  @GetMapping("/{licenseId}")
  public License getLicense(@PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId) {
    return licenseService.getLicense(organizationId, licenseId);
  }
}
