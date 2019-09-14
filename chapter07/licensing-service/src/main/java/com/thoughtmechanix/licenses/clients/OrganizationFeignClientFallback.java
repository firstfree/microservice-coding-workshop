package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.model.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrganizationFeignClientFallback implements OrganizationFeignClient {

  @Override
  public Organization getOrganization(String correlationId, String organizationId) {
    log.debug("getOrganization fallback called");

    return Organization.builder()
        .id("0000")
        .name("name")
        .contactName("contactName")
        .contactEmail("contactEmail")
        .contactPhone("contactPhone")
        .build();
  }
}
