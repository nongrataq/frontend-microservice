package com.technokratos.servlets;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.services.cards.CardService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/cards/info")
public class CardsInfoServlet extends HttpServlet {
    private CardService cardService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cardService = (CardService) config.getServletContext().getAttribute("cardService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cardId = req.getParameter("id");
        System.out.println(cardId);
        ApiResponse<CardDto> cardInfo = cardService.getCardInfo(UUID.fromString(cardId));
        req.setAttribute("card", cardInfo.getData());
        System.out.println(cardInfo.getData());
        req.getRequestDispatcher("/jsp/card-info.jsp").forward(req, resp);
    }
}
