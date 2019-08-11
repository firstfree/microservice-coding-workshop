package com.thoughtmechanix.lincenses.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtmechanix.lincenses.model.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OrganizationRestTemplateClient {

  private final RestTemplate restTemplate;

  @HystrixCommand
  public Organization getOrganization(String organizationId) {
    ResponseEntity<Organization> restExchange = restTemplate.exchange(
        "http://organizationservice/v1/organizations/{organizationId}",
        HttpMethod.GET,
        null, Organization.class, organizationId);
    return restExchange.getBody();
  }
}
