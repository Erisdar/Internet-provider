package com.epam.internet_provider.service;

import java.util.Map;

public interface JwtTokenService {

    String issueToken(String login, int role);

    Map<String, String> parseToken(String token);

    int getExpirationTimeInSeconds();
}
