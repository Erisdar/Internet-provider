package com.epam.internet_provider.controller;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "UserServlet",
    urlPatterns = {"/user"})
public class UserController extends HttpServlet {

  private UserDao userDao = new UserDaoImpl();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    Try.run(
        () -> {
          response.setContentType("application/json");
          response
              .getWriter()
              .print(
                  new ObjectMapper()
                      .writeValueAsString(
                          userDao.getUser(String.valueOf(request.getAttribute("login")))));
        });
  }
}
