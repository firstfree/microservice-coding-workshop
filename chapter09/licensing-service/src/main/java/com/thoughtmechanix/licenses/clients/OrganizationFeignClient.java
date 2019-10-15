package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.config.RequestInterceptorConfiguration;
import com.thoughtmechanix.licenses.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuulservice", fallback = OrganizationFeignClientFallback.class,
    configuration = RequestInterceptorConfiguration.class)
public interface OrganizationFeignClient {

  @GetMapping("/api/organization/v1/organizations/{organizationId}")
  Organization getOrganization(@PathVariable String organizationId);
}
