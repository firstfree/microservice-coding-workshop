package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.model.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrganizationFeignClient {

  @GetMapping("/api/organization/v1/organizations/{organizationId}")
  Organization getOrganization(@PathVariable String organizationId);
}
