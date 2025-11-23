package com.technokratos.servlets;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.models.cards.CreateCardRequest;
import com.technokratos.services.cards.CardService;
import com.technokratos.services.cards.CardServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

//ENDPOINT ТОЛЬКО ДЛЯ ТЕХ У КОГО ЕСТЬ ТОКЕН В TOKEN FILTER
@WebServlet("/order-card")
public class OrderCardServlet extends HttpServlet {

    private CardService cardService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cardService = (CardService) config.getServletContext().getAttribute("cardService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        UUID cardProductId = UUID.fromString(req.getParameter("cardProductId"));

        System.out.println(cardProductId);

        UUID userId = (UUID) session.getAttribute("userId");

        String cardName = req.getParameter("cardName");

        CreateCardRequest orderedCard = CreateCardRequest.builder()
                .cardName(cardName)
                .cardProductId(cardProductId)
                .userId(userId)
                .contractName("Договор-%s".formatted(UUID.randomUUID().toString().substring(0, 8)))
                .build();

        ApiResponse<CardDto> createdCard = cardService.orderCard(orderedCard);

        System.out.println(createdCard.toString());

        //можно добавить для отображения на фронте какие то поля из createdCart или сообщение об успешном создании карты

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
