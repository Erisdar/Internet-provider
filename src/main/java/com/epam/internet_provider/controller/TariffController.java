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
    name = "TariffServlet",
    urlPatterns = {"/tariff"})
public class TariffController extends HttpServlet {

  private UserDao userDao = new UserDaoImpl();

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
    if (userDao.updateTariff(
        String.valueOf(req.getAttribute("login")),
        JsonUtil.parseData(Try.of(req::getReader).get()).getInt("tariff_id"))) {
      resp.setStatus(Response.SC_OK);
    } else {
      resp.setStatus(Response.SC_PAYMENT_REQUIRED);
    }
  }
}
