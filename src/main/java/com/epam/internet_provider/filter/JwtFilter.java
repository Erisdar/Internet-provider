package com.epam.internet_provider.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/payment"})
public class JwtFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
//    Try.run(
//            () -> {
//              jwtTokenService
//                  .parseToken(
//                      Arrays.stream(((HttpServletRequest) request).getCookies())
//                          .filter(cookie -> "token".equals(cookie.getName()))
//                          .map(Cookie::getValue)
//                          .findFirst()
//                          .orElse(null))
//                  .entrySet()
//                  .stream()
//                  .filter(
//                      claims -> "role".equals(claims.getKey()) || "login".equals(claims.getKey()))
//                  .forEach(claims -> request.setAttribute(claims.getKey(), claims.getValue()));
//              chain.doFilter(request, response);
//            })
//        .orElseRun(throwable -> Try.run(() -> ((HttpServletResponse) response).sendRedirect("/")));
  }

  @Override
  public void destroy() {}
}
