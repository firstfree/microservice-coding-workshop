package com.thoughtmechanix.organization.services;

import com.thoughtmechanix.organization.events.source.SimpleSourceBean;
import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {

  private final OrganizationRepository organizationRepository;
  private final SimpleSourceBean simpleSourceBean;

  public Organization getOrganization(String organizationId) {
    return organizationRepository.findById(organizationId)
        .orElseThrow(() -> new NullPointerException("organizationId - " + organizationId));
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
