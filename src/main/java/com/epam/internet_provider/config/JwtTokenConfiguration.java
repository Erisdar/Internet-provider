package com.epam.internet_provider.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenConfiguration {
  // TODO COMPONENT
  private String key;
  private String name;
}
