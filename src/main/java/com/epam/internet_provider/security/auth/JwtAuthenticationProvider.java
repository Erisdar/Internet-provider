package com.epam.internet_provider.security.auth;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.model.JwtClaims;
import com.epam.internet_provider.service.JwtTokenService;

import io.jsonwebtoken.lang.Assert;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider
{

  private final JwtTokenService jwtTokenService;

  @Autowired
  public JwtAuthenticationProvider( final JwtTokenService jwtTokenService )
  {
    this.jwtTokenService = jwtTokenService;
  }

  @Override
  public Authentication authenticate( Authentication authentication ) throws AuthenticationException
  {

    final Map<String, String> claims = jwtTokenService.parseToken( (String)authentication.getCredentials() );
    final String login = claims.get( JwtClaims.login.name() );

    List<GrantedAuthority> roles = Stream.of( claims.get( JwtClaims.role.name() ) )
      .map( SimpleGrantedAuthority::new )
      .collect( Collectors.toList() );

    Assert.notNull( login );

    return new PreAuthenticatedAuthenticationToken(
      new Credentials().setLogin( login ), authentication.getCredentials(), roles );
  }

  @Override
  public boolean supports( Class<?> authentication )
  {
    return JwtAuthToken.class.equals(authentication);
  }
}
