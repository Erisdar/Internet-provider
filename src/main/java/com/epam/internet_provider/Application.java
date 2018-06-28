package com.epam.internet_provider;

import com.epam.internet_provider.controller.RegistrationController;
import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

public class Application {
    private static final String WEBAPP_RESOURCES_LOCATION = "src/main/resources/webapp/";

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(RegistrationController.class, "/registration");
        servletHandler.addServletWithMapping(JspServlet.class, "*.jsp");

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase(WEBAPP_RESOURCES_LOCATION);
        webAppContext.setServletHandler(servletHandler);

        server.setHandler(webAppContext);
        server.start();
        server.join();

    }

}
