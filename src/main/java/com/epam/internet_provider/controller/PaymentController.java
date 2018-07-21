package com.epam.internet_provider.controller;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import com.epam.internet_provider.util.JsonUtil;
import io.vavr.control.Try;
import org.eclipse.jetty.server.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "PaymentServlet",
    urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

  private UserDao userDao = new UserDaoImpl();

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
    Try.run(
            () -> {
              final String login = String.valueOf(req.getAttribute("login"));
              final int cash = JsonUtil.parseData(req.getReader()).getInt("cash");
              userDao.updateCash(login, cash);
            })
        .orElseRun(throwable -> resp.setStatus(Response.SC_SERVICE_UNAVAILABLE));
  }
}
