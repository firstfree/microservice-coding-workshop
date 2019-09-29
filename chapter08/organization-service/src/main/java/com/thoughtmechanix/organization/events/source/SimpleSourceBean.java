package com.thoughtmechanix.organization.events.source;

import com.thoughtmechanix.organization.events.models.OrganizationChangeModel;
import com.thoughtmechanix.organization.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleSourceBean {

  private final Source source;

  public void publishOrgChange(String action, String orgId) {
    log.debug("Sending Kafka message {} for organization id: {} correlation id: {}",
        action, orgId, UserContextHolder.getContext().getCorrelationId());

    OrganizationChangeModel change = OrganizationChangeModel.builder()
        .type(OrganizationChangeModel.class.getTypeName())
        .action(action)
        .organizationId(orgId)
        .correlationId(UserContextHolder.getContext().getCorrelationId())
        .build();

    source
        .output()
        .send(MessageBuilder.withPayload(change).build());
  }
}
