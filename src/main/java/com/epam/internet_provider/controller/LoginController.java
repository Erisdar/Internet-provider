package com.epam.internet_provider.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.internet_provider.config.JwtTokenConfig;
import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.repository.UserRepository;
import com.epam.internet_provider.service.JwtTokenService;
import com.epam.internet_provider.util.CookieUtil;

import lombok.val;

@RepositoryRestController
@RequestMapping(path = "/login")
public class LoginController
{

  private final UserRepository userRepository;
  private final JwtTokenService jwtTokenService;
  private final JwtTokenConfig tokenConfig;

  @Autowired
  public LoginController( UserRepository userRepository, JwtTokenService jwtTokenService, JwtTokenConfig tokenConfig )
  {
    this.userRepository = userRepository;
    this.jwtTokenService = jwtTokenService;
    this.tokenConfig = tokenConfig;
  }

  @PostMapping
  public ResponseEntity login( @RequestBody Credentials credentials, HttpServletResponse response,
    PersistentEntityResourceAssembler resourceAssembler )
  {
    return Optional.ofNullable( userRepository.findByLogin( credentials.getLogin() ) )
      // .filter( user -> HashingUtil.checkString( credentials.getPassword(), user.getPassword() ) )
      .map( user -> {
        val token = jwtTokenService.issueToken( user.getLogin(), user.getRole().name() );
        response.addCookie( CookieUtil.createCookie( token ) );
        response.setHeader( tokenConfig.getName(), token );
        return new ResponseEntity<>( resourceAssembler.toResource( user ), HttpStatus.OK );
      } )
      .orElse( new ResponseEntity<>( HttpStatus.UNAUTHORIZED ) );
  }
}
