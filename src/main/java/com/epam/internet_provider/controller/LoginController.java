package com.epam.internet_provider.controller;

import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.service.JwtTokenService;
import com.epam.internet_provider.service.LoginService;
import com.epam.internet_provider.service.impl.JwtTokeServiceImpl;
import com.epam.internet_provider.service.impl.LoginServiceImpl;
import com.epam.internet_provider.util.JsonUtil;
import io.vavr.control.Try;
import org.eclipse.jetty.server.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"}, loadOnStartup = 1)
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private JwtTokenService jwtTokenService = new JwtTokeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Credentials credentials = loginService.authenticate(JsonUtil.parseCredentials(Try.of(req::getReader).get()));

        Try.run(() -> Optional.of(credentials).ifPresent(credentials1 -> {
            String token = jwtTokenService.issueToken(credentials.getLogin(), credentials.getRole());
            resp.setHeader("Token", token);
            resp.setStatus(Response.SC_OK);
        })).orElseRun(throwable -> resp.setStatus(Response.SC_FORBIDDEN));
    }

}
