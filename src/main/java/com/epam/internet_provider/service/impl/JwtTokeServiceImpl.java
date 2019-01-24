package com.epam.internet_provider.service.impl;

import static java.time.ZoneOffset.UTC;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.internet_provider.config.JwtTokenConfig;
import com.epam.internet_provider.model.JwtClaims;
import com.epam.internet_provider.service.JwtTokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vavr.control.Try;

@Service
public class JwtTokeServiceImpl implements JwtTokenService {

  private final JwtTokenConfig jwtConfig;

  @Autowired
  public JwtTokeServiceImpl(final JwtTokenConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  @Override
  public String issueToken(String login, String role) {
    return Try.of(
            () ->
                Jwts.builder()
                    .setExpiration(
                        Date.from(
                            LocalDateTime.now()
                                .plusHours(jwtConfig.getExpirationHours())
                                .toInstant(UTC)))
                    .claim(JwtClaims.login.name(), login)
                    .claim(JwtClaims.role.name(), role)
                    .signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                    .compact())
        .getOrNull();
  }

  @Override
  public Map<String, String> parseToken(String token) {
    return Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(token).getBody()
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, t -> String.valueOf(t.getValue())));
  }

  @Override
  public int getExpirationTimeInSeconds() {
    return jwtConfig.getExpirationHours() * 60 * 60;
  }
}
