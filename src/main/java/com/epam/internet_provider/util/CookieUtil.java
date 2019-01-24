package com.epam.internet_provider.util;

import javax.servlet.http.Cookie;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CookieUtil
{

  /**
   * Create cookie for user with jwt-token
   * @param token The jwt token
   * @return Cookie object with jwt-token.
   */
  public static Cookie createCookie( String token )
  {
    Cookie cookie = new Cookie( "token", token );
    cookie.setMaxAge( 60 * 120 );
    return cookie;
  }
}
