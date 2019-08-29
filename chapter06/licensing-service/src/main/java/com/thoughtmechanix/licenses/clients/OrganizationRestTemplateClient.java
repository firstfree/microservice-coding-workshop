package com.thoughtmechanix.licenses.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.utils.UserContext;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import javax.print.DocFlavor.STRING;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
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

    HttpHeaders headers = new HttpHeaders();
    headers.set(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    ResponseEntity<Organization> restExchange = restTemplate.exchange(
        "http://zuulservice/api/organization/v1/organizations/{organizationId}",
        HttpMethod.GET,
        httpEntity, Organization.class, organizationId);
    return restExchange.getBody();
  }
}
