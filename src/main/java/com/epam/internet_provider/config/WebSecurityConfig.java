package com.epam.internet_provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epam.internet_provider.security.auth.JwtAuthFilter;
import com.epam.internet_provider.security.auth.JwtAuthenticationProvider;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationProvider authProvider;
  private final JwtAuthFilter authFilter;

  @Autowired
  public WebSecurityConfig(
      final JwtAuthenticationProvider authProvider, final JwtAuthFilter authFilter) {
    this.authProvider = authProvider;
    this.authFilter = authFilter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/style/**", "app/app.js", "app/login.js", "/main.html")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/")
        .permitAll()
        .and()
        .csrf()
        .disable()
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
