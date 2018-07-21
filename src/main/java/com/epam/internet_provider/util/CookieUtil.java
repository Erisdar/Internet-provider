package com.epam.internet_provider.util;

import com.epam.internet_provider.model.Role;
import com.epam.internet_provider.service.JwtTokenService;
import com.epam.internet_provider.service.impl.JwtTokeServiceImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CookieUtil {

  private static JwtTokenService jwtTokenService = new JwtTokeServiceImpl();

  public static Cookie createCookie(String login, Role role) {
    Cookie cookie = new Cookie("token", jwtTokenService.issueToken(login, role.name()));
    cookie.setMaxAge(jwtTokenService.getExpirationTimeInSeconds());
    return cookie;
  }
}
