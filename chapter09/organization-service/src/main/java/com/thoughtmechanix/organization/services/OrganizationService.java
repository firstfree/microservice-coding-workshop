package com.thoughtmechanix.organization.services;

import brave.Tracer;
import brave.Tracer.SpanInScope;
import com.thoughtmechanix.organization.events.source.SimpleSourceBean;
import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationService {

  private final OrganizationRepository organizationRepository;
  private final SimpleSourceBean simpleSourceBean;
  private final Tracer tracer;

  public Organization getOrganization(String organizationId) {
    brave.Span newSpan = tracer.nextSpan().name("getOrganizationDBCall");
    
    log.debug("In the organizationService.getOrganization() call");

    try (SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
      return organizationRepository.findById(organizationId)
          .orElseThrow(() -> new NullPointerException("organizationId - " + organizationId));
    } finally {
      newSpan.tag("peer.service", "mysql");
      newSpan.annotate("cr");
      newSpan.finish();
    }
  }

  public void saveOrganization(Organization organization) {
    organization.setId(UUID.randomUUID().toString());
    organizationRepository.save(organization);
    simpleSourceBean.publishOrgChange("SAVE", organization.getId());
  }

  public void updateOrganization(Organization organization) {
    organizationRepository.save(organization);
    simpleSourceBean.publishOrgChange("UPDATE", organization.getId());
  }

  public void deleteOrganization(String organizationId) {
    organizationRepository.deleteById(organizationId);
    simpleSourceBean.publishOrgChange("DELETE", organizationId);
  }
}
