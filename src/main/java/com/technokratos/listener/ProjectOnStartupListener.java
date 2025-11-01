package com.technokratos.listener;

import com.technokratos.services.SignUpService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ProjectOnStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        SignUpService signUpService = new SignUpService();

        servletContext.setAttribute("signUpService", signUpService);

    }
}
