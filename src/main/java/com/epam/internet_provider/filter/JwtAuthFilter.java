package com.epam.internet_provider.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.epam.internet_provider.security.auth.JwtAuthToken;

@Service
public class JwtAuthFilter implements Filter
{
  @Override
  public void init( FilterConfig filterConfig )
  {
  }

  @Override
  public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
    throws IOException, ServletException
  {
    Optional.ofNullable( ( (HttpServletRequest)request ).getHeader( "Authorization" ) )
      .ifPresent(
        authorization -> SecurityContextHolder.getContext()
          .setAuthentication( new JwtAuthToken( authorization.replaceAll( "Bearer ", "" ) ) ) );

    Optional.ofNullable( ( (HttpServletRequest)request ).getCookies() )
      .ifPresent(
        cookies -> Arrays.stream( cookies )
          .filter( cookie -> "token".equals( cookie.getName() ) )
          .map( Cookie::getValue )
          .findFirst()
          .ifPresent(
            authorization -> SecurityContextHolder.getContext()
              .setAuthentication( new JwtAuthToken( authorization.replaceAll( "Bearer ", "" ) ) ) ) );

    chain.doFilter( request, response );
  }

  @Override
  public void destroy()
  {
  }
}
