package com.epam.internet_provider.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"}, loadOnStartup = 1)
public class RegistrationController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameterMap().forEach((key, value) -> System.out.println(key + ": " +
                Arrays.toString(value)));
        resp.sendRedirect("index.jsp");
    }
}
