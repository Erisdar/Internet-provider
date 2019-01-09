package com.epam.internet_provider.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtTokenConfig {
  @Value("${jwt.token.key}")
  private String key;

  @Value("${jwt.token.name}")
  private String name;

  @Value("${jwt.token.expirationHours}")
  private int expirationHours;
}
