package com.thoughtmechanix.licenses.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrganizationRestTemplateClient {

  private final RestTemplate restTemplate;

  @HystrixCommand
  public Organization getOrganization(String organizationId) {
    log.debug(">>> In Licensing Service.getOrganization: {}, Thread id: {}",
        UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
    
    ResponseEntity<Organization> restExchange = restTemplate.exchange(
        "http://zuulservice/api/organization/v1/organizations/{organizationId}",
        HttpMethod.GET,
        null, Organization.class, organizationId);
    return restExchange.getBody();
  }
}
