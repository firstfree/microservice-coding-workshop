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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

  private final OrganizationService organizationService;

  @GetMapping("/{organizationId}")
  public Organization getOrganization(@PathVariable String organizationId) {
    log.debug("Looking up data for organization id: {}", organizationId);
    return organizationService.getOrganization(organizationId);
  }

  @PutMapping("/{organizationId}")
  public void updateOrganization(@PathVariable String organizationId,
      @RequestBody Organization organization) {
    organization.setId(organizationId);
    organizationService.updateOrganization(organization);
  }

  @PostMapping
  public void saveOrganization(@RequestBody Organization organization) {
    organizationService.saveOrganization(organization);
  }

  @DeleteMapping("/{organizationId}")
  public void deleteOrganization(@PathVariable String organizationId) {
    organizationService.deleteOrganization(organizationId);
  }
}
