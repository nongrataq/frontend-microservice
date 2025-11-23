package com.technokratos.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.services.*;
import com.technokratos.services.cards.CardService;
import com.technokratos.services.cards.CardServiceImpl;
import com.technokratos.services.cards.MockCardServiceImpl;
import com.technokratos.services.user_data.*;
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

        AuthService authService = new AuthServiceImpl(mapper, client);
        CardService cardService = new MockCardServiceImpl();
        TransferService transferService = new MockTransferService();
        GetBalanceService getBalanceService = new MockGetBalanceService();
        ContractService contractService = new MockContractService();
        UserRestrictionsService userRestrictionsService = new MockUserRestrictionsService();



        servletContext.setAttribute("transferService", transferService);
        servletContext.setAttribute("cardService", cardService );
        servletContext.setAttribute("authService", authService);
        servletContext.setAttribute("getBalanceService", getBalanceService);
        servletContext.setAttribute("contractService", contractService);
        servletContext.setAttribute("userRestrictionsService", userRestrictionsService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (client != null) {
            client.connectionPool().evictAll();
        }
    }
}
