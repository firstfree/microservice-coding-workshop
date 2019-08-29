package com.thoughtmechanix.organization.controllers;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

  private final OrganizationService organizationService;

  @GetMapping("/{organizationId}")
  public Organization getOrganization(@RequestHeader("tmx-correlation-id") String correlationId,
      @PathVariable("organizationId") String organizationId) {
    log.debug("tmx-correlation-id: {}", correlationId);

    Organization organization = organizationService.getOrganization(organizationId);
    organization.setContactName("OLD::" + organization.getContactName());
    return organization;
  }

  @PutMapping("/{organizationId}")
  public void updateOrganization(@PathVariable("organizationId") String organizationId,
      @RequestBody Organization organization) {
    organizationService.updateOrganization(organization);
  }

  @PostMapping
  public void saveOrganization(@RequestBody Organization organization) {
    organizationService.saveOrganization(organization);
  }

  @DeleteMapping("/{organizationId}")
  public void deleteOrganization(@PathVariable("organizationId") String organizationId) {
    organizationService.deleteOrganization(organizationId);
  }
}
