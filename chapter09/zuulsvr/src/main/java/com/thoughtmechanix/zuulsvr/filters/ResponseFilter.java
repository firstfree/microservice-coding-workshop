package com.thoughtmechanix.zuulsvr.filters;

import brave.Tracer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResponseFilter extends ZuulFilter {

  private static final int FILTER_ORDER = 1;
  private static final boolean SHOULD_FILTER = true;

  private final Tracer tracer;

  @Override
  public String filterType() {
    return "post";
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
    ctx.getResponse().addHeader("tmx-correlation-id",
        tracer.currentSpan().context().traceIdString());

    return null;
  }
}
