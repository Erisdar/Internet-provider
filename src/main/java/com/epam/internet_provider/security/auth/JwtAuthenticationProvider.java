package com.epam.internet_provider.security.auth;

import com.epam.internet_provider.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider {
  private final JwtTokenService jwtTokenService;

  @Autowired
  public JwtAuthenticationProvider(final JwtTokenService jwtTokenService) {
    this.jwtTokenService = jwtTokenService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String token = (String) authentication.getCredentials();

    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return false;
  }
}
