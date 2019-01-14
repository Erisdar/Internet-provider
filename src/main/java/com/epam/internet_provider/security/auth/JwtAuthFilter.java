package com.epam.internet_provider.security.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

public class JwtAuthFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    Optional.ofNullable(((HttpServletRequest) request).getHeader("Authorization"))
        .ifPresent(
            authorization -> {
              final JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
              SecurityContextHolder.getContext().setAuthentication(token);
            });

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {}
}
