package com.thoughtmechanix.licenses.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserContextFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    UserContext context = UserContextHolder.getContext();
    context.setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
    context.setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
    context.setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
    context.setOrgId(httpServletRequest.getHeader(UserContext.ORG_ID));

    log.debug("UserContextFilter Correlation id: {}", context.getCorrelationId());

    filterChain.doFilter(httpServletRequest, servletResponse);
  }
}
