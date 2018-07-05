package com.epam.internet_provider.service;

public interface JwtTokenService {

    String issueToken(String login, int role);

}
