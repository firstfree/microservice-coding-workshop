package com.thoughtmechanix.licenses.config;

import com.thoughtmechanix.licenses.utils.UserContext;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@Configuration
public class RequestInterceptorConfiguration {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header(UserContext.CORRELATION_ID, getCorrelationId());
      requestTemplate.header(HttpHeaders.AUTHORIZATION, getToken());
    };
  }

  private String getCorrelationId() {
    return UserContextHolder.getContext().getCorrelationId();
  }

  private String getToken() {
    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getDetails();
    return "bearer " + details.getTokenValue();
  }
}
