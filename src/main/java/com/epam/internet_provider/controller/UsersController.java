//package com.epam.internet_provider.controller;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.catalina.connector.Response;
//
//import com.epam.internet_provider.dao.UserDao;
//import com.epam.internet_provider.dao.impl.UserDaoImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.vavr.control.Try;
//
//@WebServlet(
//    name = "UsersServlet",
//    urlPatterns = {"/users2"})
//public class UsersController extends HttpServlet {
//
//  private UserDao userDao = new UserDaoImpl();
//
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse response) {
//    Try.run(
//            () -> {
//              response.setContentType("application/json");
//              response.getWriter().print(new ObjectMapper().writeValueAsString(userDao.getUsers()));
//            })
//        .orElseRun(throwable -> response.setStatus(Response.SC_UNAUTHORIZED));
//  }
//}
