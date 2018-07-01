package com.epam.internet_provider.controller;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import com.epam.internet_provider.util.UserUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"}, loadOnStartup = 1)
public class RegistrationController extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //return boolean
        userDao.registerUser(UserUtil.createDefaultUser(req.getParameterMap()));

        resp.sendRedirect("index.jsp");
    }
}
