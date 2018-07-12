package com.epam.internet_provider.controller;

import com.epam.internet_provider.model.User;
import com.epam.internet_provider.service.JwtTokenService;
import com.epam.internet_provider.service.LoginService;
import com.epam.internet_provider.service.impl.JwtTokeServiceImpl;
import com.epam.internet_provider.service.impl.LoginServiceImpl;
import com.epam.internet_provider.util.JsonUtil;
import io.vavr.control.Try;
import org.eclipse.jetty.server.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private JwtTokenService jwtTokenService = new JwtTokeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = loginService.authenticate(JsonUtil.parseCredentials(Try.of(req::getReader).get()));

        Try.run(() -> Optional.of(user).ifPresent(user1 -> {
            String token = jwtTokenService.issueToken(user.getLogin(), user.getRole().name());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(jwtTokenService.getExpirationTimeInSeconds());
            resp.addCookie(cookie);
            resp.setHeader("Token", token);
            resp.setHeader("User", user.getLogin());
            resp.setStatus(Response.SC_OK);
        })).orElseRun(throwable -> resp.setStatus(Response.SC_FORBIDDEN));

    }
}
