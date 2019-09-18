package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thoughtmechanix.zuulsvr.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResponseFilter extends ZuulFilter {

  private static final int FILTER_ORDER = 1;
  private static final boolean SHOULD_FILTER = true;

  private final FilterUtils filterUtils;

  @Override
  public String filterType() {
    return FilterUtils.POST_FILTER_TYPE;
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
    RequestContext ctx = RequestContext.getCurrentContext();

    log.debug("Adding the correlation id to the outbound headers. {}",
        filterUtils.getCorrelationId());

    ctx.getResponse().addHeader(UserContext.CORRELATION_ID, filterUtils.getCorrelationId());

    log.debug("Completing outgoing request for {}.", ctx.getRequest().getRequestURI());

    return null;
  }
}
