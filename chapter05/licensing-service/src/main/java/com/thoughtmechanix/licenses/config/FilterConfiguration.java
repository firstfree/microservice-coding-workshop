package com.thoughtmechanix.licenses.config;

import ch.qos.logback.access.servlet.TeeFilter;
import java.util.Arrays;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean requestResponseFilter() {
    final FilterRegistrationBean filterReqBean = new FilterRegistrationBean();
    filterReqBean.setFilter(new TeeFilter());
    filterReqBean.setUrlPatterns(Arrays.asList("/v1/*"));
    filterReqBean.setName("Request Response Filter");
    filterReqBean.setAsyncSupported(true);
    return filterReqBean;
  }
}
