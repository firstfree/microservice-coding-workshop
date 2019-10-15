package com.thoughtmechanix.licenses.events.handlers;

import com.thoughtmechanix.licenses.events.CustomChannels;
import com.thoughtmechanix.licenses.events.models.OrganizationChangeModel;
import com.thoughtmechanix.licenses.repository.OrganizationRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(CustomChannels.class)
@RequiredArgsConstructor
public class OrganizationChangeHandler {

  private final OrganizationRedisRepository organizationRedisRepository;

  @StreamListener("inboundOrgChanges")
  public void loggerSink(OrganizationChangeModel orgChange) {
    log.debug("Received a message of type {}", orgChange.getType());

    switch (orgChange.getAction()) {
      case "GET":
        log.debug("Received a GET event from the organization service for organization id {}",
            orgChange.getOrganizationId());
        break;
      case "SAVE":
        log.debug("Received a SAVE event from the organization service for organization id {}",
            orgChange.getOrganizationId());
        break;
      case "UPDATE":
        log.debug("Received a UPDATE event from the organization service for organization id {}",
            orgChange.getOrganizationId());
        organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
        break;
      case "DELETE":
        log.debug("Received a DELETE event from the organization service for organization id {}",
            orgChange.getOrganizationId());
        organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
        break;
      default:
        log.debug("Received an UNKNOWN event from the organization service of type {}",
            orgChange.getType());
    }
  }
}
