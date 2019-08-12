package com.thoughtmechanix.licenses.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

  @Value("${example.property")
  private String exampleProperty;

  public String getExampleProperty() {
    return exampleProperty;
  }
}
