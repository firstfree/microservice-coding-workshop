package com.thoughtmechanix.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class OAuth2Config implements AuthorizationServerConfigurer {

  @Override
  public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer)
      throws Exception {

  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer)
      throws Exception {

  }

  @Override
  public void configure(
      AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer)
      throws Exception {

  }
}
