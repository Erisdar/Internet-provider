package com.epam.internet_provider.service.impl;

import com.epam.internet_provider.config.JwtTokenConfiguration;
import com.epam.internet_provider.service.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vavr.control.Try;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;

public class JwtTokeServiceImpl implements JwtTokenService {

    private static final String TOKEN_CONFIG_PATH = "application.yml";
    private static final String JWT_ROOT = "jwt";
    private static final String JWT_SUB_ROOT = "token";
    private static final String JWT_KEY = "key";
    private static final String JWT_NAME = "name";
    private static final int EXPIRATION_HOURS = 2;

    @Override
    public String issueToken(String login, int role) {
        return Try.of(() -> Jwts.builder()
                .setExpiration(Date.from(LocalDateTime.now().plusHours(EXPIRATION_HOURS).toInstant(UTC)))
                .claim("login", login)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, readToken().getKey())
                .compact()).get();
    }

    @Override
    public Map<String, String> parseToken(String token) {
        return Jwts.parser().setSigningKey(readToken().getKey()).parseClaimsJws(token).getBody().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, t -> String.valueOf(t.getValue())));
    }

    @Override
    public int getExpirationTimeInSeconds() {
        return EXPIRATION_HOURS * 60 * 60;
    }

    @SuppressWarnings("unchecked")
    public JwtTokenConfiguration readToken() {
        return Try.withResources(() -> new FileInputStream(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(TOKEN_CONFIG_PATH)).getFile()))
                .of(stream -> Optional.of((Map<String, Map<String, Map<String, String>>>) (new Yaml().load(stream)))
                        .map(ymlMap -> new JwtTokenConfiguration(ymlMap.get(JWT_ROOT).get(JWT_SUB_ROOT).get(JWT_KEY),
                                ymlMap.get(JWT_ROOT).get(JWT_SUB_ROOT).get(JWT_NAME))).get()).get();
    }
}
