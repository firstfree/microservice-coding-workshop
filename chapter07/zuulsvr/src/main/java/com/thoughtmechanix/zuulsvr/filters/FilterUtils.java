package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.context.RequestContext;
import com.thoughtmechanix.zuulsvr.utils.UserContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

  public static final String PRE_FILTER_TYPE = "pre";
  public static final String POST_FILTER_TYPE = "post";
  public static final String ROUTE_FILTER_TYPE = "route";

  public String getCorrelationId() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(UserContext.CORRELATION_ID) != null) {
      return ctx.getRequest().getHeader(UserContext.CORRELATION_ID);
    }

    return ctx.getZuulRequestHeaders().get(UserContext.CORRELATION_ID);
  }

  public void setCorrelationId(String correlationId) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(UserContext.CORRELATION_ID, correlationId);
  }

  public String getUserId() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(UserContext.USER_ID) != null) {
      return ctx.getRequest().getHeader(UserContext.USER_ID);
    }

    return ctx.getZuulRequestHeaders().get(UserContext.USER_ID);
  }

  public void setUserId(String userId) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(UserContext.USER_ID, userId);
  }

  public String getAuthToken() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(UserContext.AUTH_TOKEN) != null) {
      return ctx.getRequest().getHeader(UserContext.AUTH_TOKEN);
    }

    return ctx.getZuulRequestHeaders().get(UserContext.AUTH_TOKEN);
  }

  public void setAuthToken(String authToken) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(UserContext.AUTH_TOKEN, authToken);
  }

  public String getOrgId() {
    RequestContext ctx = RequestContext.getCurrentContext();

    if (ctx.getRequest().getHeader(UserContext.ORG_ID) != null) {
      return ctx.getRequest().getHeader(UserContext.ORG_ID);
    }

    return ctx.getZuulRequestHeaders().get(UserContext.ORG_ID);
  }

  public void setOrgId(String orgId) {
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader(UserContext.ORG_ID, orgId);
  }
}
