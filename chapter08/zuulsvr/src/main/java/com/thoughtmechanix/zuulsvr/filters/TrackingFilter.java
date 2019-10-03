package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrackingFilter extends ZuulFilter {

  private static final int FILTER_ORDER = 1;
  private static final boolean SHOULD_FILTER = true;

  private final FilterUtils filterUtils;

  @Override
  public String filterType() {
    return FilterUtils.PRE_FILTER_TYPE;
  }

  @Override
  public int filterOrder() {
    return FILTER_ORDER;
  }

  @Override
  public boolean shouldFilter() {
    return SHOULD_FILTER;
  }

  @Override
  public Object run() {
    if (isCorrelationIdPresent()) {
      log.debug("tmx-correlation-id found in tracking filter: {}.", filterUtils.getCorrelationId());
    } else {
      filterUtils.setCorrelationId(generateCorrelationId());
      log.debug("tmx-correlation-id generated in tracking filter: {}.",
          filterUtils.getCorrelationId());
    }

    RequestContext ctx = RequestContext.getCurrentContext();

    log.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());

    return null;
  }

  private boolean isCorrelationIdPresent() {
    return filterUtils.getCorrelationId() != null;
  }

  private String generateCorrelationId() {
    return UUID.randomUUID().toString();
  }
}
