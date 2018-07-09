package com.epam.internet_provider.controller;

import com.epam.internet_provider.dao.TariffDao;
import com.epam.internet_provider.dao.impl.TariffDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TariffServlet", urlPatterns = {"/tariff"})
public class TariffController extends HttpServlet {

    private TariffDao tariffDao = new TariffDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().println(tariffDao.getTariffs());
    }
}
