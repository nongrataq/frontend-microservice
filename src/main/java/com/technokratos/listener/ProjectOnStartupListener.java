package com.technokratos.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.services.SignInService;
import com.technokratos.services.SignUpService;
import com.technokratos.services.TokenService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import okhttp3.OkHttpClient;

@WebListener
public class ProjectOnStartupListener implements ServletContextListener {

    private OkHttpClient client;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        SignUpService signUpService = new SignUpService(client, mapper);
        SignInService signInService = new SignInService(client, mapper);
        TokenService tokenService = new TokenService(client, mapper);

        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("tokenService", tokenService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (client != null) {
            client.connectionPool().evictAll();
        }
    }
}
