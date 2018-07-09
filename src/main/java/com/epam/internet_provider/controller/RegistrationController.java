package com.epam.internet_provider.controller;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import com.epam.internet_provider.util.UserUtil;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (userDao.registerUser(UserUtil.createDefaultUser(new JSONObject(req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual))))) {
            resp.setStatus(201);
        } else {
            resp.setStatus(400);
        }
    }
}
