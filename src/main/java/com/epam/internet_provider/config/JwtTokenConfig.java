package com.epam.internet_provider.config;

import io.vavr.control.Try;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Objects;

@Data
public class JwtTokenConfig {

  private static final String TOKEN_CONFIG_PATH = "application.yml";
  private static final String JWT_ROOT = "jwt";
  private static final String JWT_KEY = "key";
  private static final String JWT_NAME = "name";
  private static final String JWT_EXPIRATION = "expirationHours";
  private String key;
  private String name;
  private int expirationHours;

  @SuppressWarnings("unchecked")
  private JwtTokenConfig() {
    Try.withResources(
            () ->
                new FileInputStream(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource(TOKEN_CONFIG_PATH))
                        .getFile()))
        .of(stream -> (Map<String, Map<String, ?>>) new Yaml().load(stream))
        .forEach(
            configMap -> {
              this.key = (String) configMap.get(JWT_ROOT).get(JWT_KEY);
              this.name = (String) configMap.get(JWT_ROOT).get(JWT_NAME);
              this.expirationHours = (Integer) configMap.get(JWT_ROOT).get(JWT_EXPIRATION);
            });
  }

  private static class LazyJwtConfigHolder {
    static final JwtTokenConfig INSTANCE = new JwtTokenConfig();
  }

  public static JwtTokenConfig getInstance() {
    return LazyJwtConfigHolder.INSTANCE;
  }
}
