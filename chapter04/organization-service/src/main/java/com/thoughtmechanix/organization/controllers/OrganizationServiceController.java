package com.thoughtmechanix.organization.controllers;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.services.OrganizationService;
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
@RequestMapping("/v1/organizations")
public class OrganizationServiceController {

  @Autowired
  private OrganizationService organizationService;

  @GetMapping("/{organizationId}")
  public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
    return organizationService.getOrganization(organizationId);
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
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrganization(@PathVariable("organizationId") String organizationId) {
    organizationService.deleteOrganization(organizationId);
  }
}
