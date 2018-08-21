package com.epam.internet_provider.controller;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import io.vavr.control.Try;
import org.eclipse.jetty.server.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "UserDataServlet",
    urlPatterns = {"/userData"})
public class UserDataController extends HttpServlet {
  private UserDao userDao = new UserDaoImpl();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    Try.run(
            () -> {
              response.setStatus(Response.SC_OK);
              response
                  .getWriter()
                  .print(
                      userDao.getData(
                          request.getParameter("value"), request.getParameter("field")));
            })
        .orElseRun(throwable -> response.setStatus(Response.SC_BAD_REQUEST));
  }
}
