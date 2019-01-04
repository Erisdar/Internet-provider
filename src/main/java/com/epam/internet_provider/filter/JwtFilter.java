package com.epam.internet_provider.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.epam.internet_provider.service.JwtTokenService;
import com.epam.internet_provider.service.impl.JwtTokeServiceImpl;

@WebFilter(urlPatterns = {"/payment"})
public class JwtFilter implements Filter {
  private JwtTokenService jwtTokenService = new JwtTokeServiceImpl();

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
    //                      claims -> "role".equals(claims.getKey()) ||
    // "login".equals(claims.getKey()))
    //                  .forEach(claims -> request.setAttribute(claims.getKey(),
    // claims.getValue()));
    //              chain.doFilter(request, response);
    //            })
    //        .orElseRun(throwable -> Try.run(() -> ((HttpServletResponse)
    // response).sendRedirect("/")));
  }

  @Override
  public void destroy() {}
}
