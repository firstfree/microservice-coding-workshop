package com.thoughtmechanix.authentication.security;

import com.thoughtmechanix.authentication.model.UserOrganization;
import com.thoughtmechanix.authentication.repository.OrgUserRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTTokenEnhancer implements TokenEnhancer {

  private final OrgUserRepository orgUserRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    Map<String, Object> additionalInfo = new HashMap<>();
    String organizationId = getOrganizationId(authentication.getName());
    additionalInfo.put("organizationId", organizationId);
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }

  private String getOrganizationId(String userName) {
    UserOrganization userOrganization = orgUserRepository.findById(userName)
      .orElseThrow(NullPointerException::new);
    return userOrganization.getOrganizationId();
  }
}
