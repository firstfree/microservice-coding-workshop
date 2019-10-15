package com.thoughtmechanix.licenses.repository;

import com.thoughtmechanix.licenses.model.Organization;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {

  private static final String HASH_NAME = "organization";

  private final RedisTemplate redisTemplate;
  private HashOperations hashOperations;

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public void saveOrganization(Organization organization) {
    hashOperations.put(HASH_NAME, organization.getId(), organization);
  }

  @Override
  public void updateOrganization(Organization organization) {
    hashOperations.put(HASH_NAME, organization.getId(), organization);
  }

  @Override
  public void deleteOrganization(String organizationId) {
    hashOperations.delete(HASH_NAME, organizationId);
  }

  @Override
  public Organization findOrganization(String organizationId) {
    return (Organization) hashOperations.get(HASH_NAME, organizationId);
  }
}
