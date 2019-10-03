package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

  public static final String CORRELATION_ID = "tmx-correlation-id";
  public static final String USER_ID = "tmx-user-id";
  public static final String AUTH_TOKEN = "tmx-auth-token";
  public static final String ORG_ID = "tmx-org-id";
  public static final String PRE_FILTER_TYPE = "pre";
  public static final String POST_FILTER_TYPE = "post";
  public static final String ROUTE_FILTER_TYPE = "route";

  public String getCorrelationId() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(CORRELATION_ID) != null) {
      return ctx.getRequest().getHeader(CORRELATION_ID);
    }

    return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
  }

  public void setCorrelationId(String correlationId) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
  }

  public String getUserId() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(USER_ID) != null) {
      return ctx.getRequest().getHeader(USER_ID);
    }

    return ctx.getZuulRequestHeaders().get(USER_ID);
  }

  public void setUserId(String userId) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(USER_ID, userId);
  }

  public String getAuthToken() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(AUTH_TOKEN) != null) {
      return ctx.getRequest().getHeader(AUTH_TOKEN);
    }

    return ctx.getZuulRequestHeaders().get(AUTH_TOKEN);
  }

  public void setAuthToken(String authToken) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(AUTH_TOKEN, authToken);
  }

  public String getOrgId() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(ORG_ID) != null) {
      return ctx.getRequest().getHeader(ORG_ID);
    }

    return ctx.getZuulRequestHeaders().get(ORG_ID);
  }

  public void setOrgId(String orgId) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(ORG_ID, orgId);
  }
}
