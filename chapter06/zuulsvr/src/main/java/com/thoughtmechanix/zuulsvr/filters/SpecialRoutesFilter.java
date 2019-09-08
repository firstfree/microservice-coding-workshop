package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.thoughtmechanix.zuulsvr.model.AbTestingRoute;
import com.thoughtmechanix.zuulsvr.utils.HttpUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class SpecialRoutesFilter extends ZuulFilter {

  private static final Logger logger = LoggerFactory.getLogger(SpecialRoutesFilter.class);

  private static final int FILTER_ORDER = 1;
  private static final boolean SHOULD_FILTER = true;

  private FilterUtils filterUtils;
  private RestTemplate restTemplate;
  private ProxyRequestHelper helper;

  public SpecialRoutesFilter(FilterUtils filterUtils, RestTemplate restTemplate,
      ProxyRequestHelper helper) {
    this.filterUtils = filterUtils;
    this.restTemplate = restTemplate;
    this.helper = helper;
  }

  @Override
  public String filterType() {
    return FilterUtils.ROUTE_FILTER_TYPE;
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
    logger.debug("Route host: {}", filterUtils.getRouteHost());

    AbTestingRoute abTestingRoute = getAbTestingRoute(filterUtils.getRouteHost());
    if (abTestingRoute != null && abTestingRoute.useSpecialRoute(10)) {
      forward(abTestingRoute.getEndpoint());
    }

    return null;
  }

  private AbTestingRoute getAbTestingRoute(String routeHost) {
    try {
      ResponseEntity<AbTestingRoute> restExchange = restTemplate.getForEntity(
          "http://specialroutesservice/v1/route/abtesting/{routeHost}",
          AbTestingRoute.class, routeHost);
      return restExchange.getBody();
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        return null;
      }

      throw e;
    }
  }

  private void forward(String host) {
    try {
      RequestContext context = RequestContext.getCurrentContext();
      HttpServletRequest request = context.getRequest();

      String url = HttpUtils.makeUrl("http", host, this.helper.buildZuulRequestURI(request));
      Request newRequest = HttpUtils.makeRequest(url, request);

      Response response = HttpUtils.call(newRequest);

      MultiValueMap<String, String> responseHeaders = HttpUtils.makeResponseHeaders(response);

      this.helper.setResponse(response.code(), response.body().byteStream(), responseHeaders);

      context.setRouteHost(null);   // SimpleHostRoutingFilter 호출을 막기 위함
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
