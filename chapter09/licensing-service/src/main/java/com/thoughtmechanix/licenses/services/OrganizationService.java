package com.thoughtmechanix.licenses.services;

import brave.Tracer;
import brave.Tracer.SpanInScope;
import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.OrganizationRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationService {

  private final OrganizationFeignClient organizationFeignClient;
  private final OrganizationRedisRepository organizationRedisRepository;
  private final Tracer tracer;

  public Organization getOrganization(String organizationId) {
    Organization organization = checkCache(organizationId);

    if (organization != null) {
      log.debug("I have successfully retrieved an organization {} from the redis cache: {}",
          organizationId, organization);
      return organization;
    }

    log.debug("Unable to locate organization from the redis cache: {}", organizationId);

    organization = organizationFeignClient.getOrganization(organizationId);

    if (organization != null) {
      cacheOrganization(organization);
    }

    return organization;
  }

  private Organization checkCache(String organizationId) {
    brave.Span newSpan = tracer.nextSpan().name("readLicensingDataFromRedis");

    try (SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
      return organizationRedisRepository.findOrganization(organizationId);
    } catch (Exception e) {
      log.error("Error encountered while trying to retrieve organization {}. Exception {}",
          organizationId, e);
      return null;
    } finally {
      newSpan.tag("peer.service", "redis");
      newSpan.annotate("cr");
      newSpan.finish();
    }
  }

  private void cacheOrganization(Organization organization) {
    try {
      organizationRedisRepository.saveOrganization(organization);
    } catch (Exception e) {
      log.error("Unable to cache organization {}. Exception {}", organization.getId(), e);
    }
  }
}
