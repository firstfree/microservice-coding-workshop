package com.thoughtmechanix.licenses.config;

import com.thoughtmechanix.licenses.utils.UserContext;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestInterceptorConfiguration {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate ->
      requestTemplate.header(UserContext.CORRELATION_ID, getCorrelationId());
  }

  private String getCorrelationId() {
    return UserContextHolder.getContext().getCorrelationId();
  }
}
