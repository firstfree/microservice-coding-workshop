package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.utils.UserContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "zuulservice", fallback = OrganizationFeignClientFallback.class)
public interface OrganizationFeignClient {

  @GetMapping("/api/organization/v1/organizations/{organizationId}")
  Organization getOrganization(@RequestHeader(UserContext.CORRELATION_ID) String correlationId,
      @PathVariable String organizationId);
}
