package com.epam.internet_provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epam.internet_provider.filter.JwtAuthFilter;
import com.epam.internet_provider.model.Role;
import com.epam.internet_provider.security.auth.JwtAuthenticationProvider;

import lombok.val;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

  private final JwtAuthenticationProvider authProvider;
  private final JwtAuthFilter authFilter;

  @Autowired
  public WebSecurityConfig( JwtAuthenticationProvider authProvider, JwtAuthFilter authFilter )
  {
    this.authProvider = authProvider;
    this.authFilter = authFilter;
  }

  @Override
  public void configure( AuthenticationManagerBuilder auth )
  {
    auth.authenticationProvider( authProvider );
  }

  @Override
  protected void configure( HttpSecurity http ) throws Exception
  {

    val patterns = new String[] {
      "/", "/app/**/*", "/components/*", "/style/**/*", "/img/**/*", "/locale/**/*", "/users/search/login",
      "/users/search/email", "/encrypt", "/login", "/signin"
    };

    http.authorizeRequests()
      .antMatchers( patterns )
      .permitAll()
      .anyRequest()
      .hasAnyAuthority( Role.User.name(), Role.Admin.name() )
      .and()
      .formLogin()
      .loginPage( "/signin" )
      .successForwardUrl( "/" )
      .and()
      .csrf()
      .disable()
      .addFilterBefore( authFilter, UsernamePasswordAuthenticationFilter.class )
      .exceptionHandling()
      .and()
      .sessionManagement()
      .sessionCreationPolicy( SessionCreationPolicy.STATELESS );
  }
}
