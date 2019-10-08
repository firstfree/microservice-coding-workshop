package com.thoughtmechanix.licenses.repository;

import com.thoughtmechanix.licenses.model.Organization;

public interface OrganizationRedisRepository {

  void saveOrganization(Organization organization);
  void updateOrganization(Organization organization);
  void deleteOrganization(String organizationId);
  Organization findOrganization(String organizationId);
}
