package com.technokratos.servlets;

import com.technokratos.models.cards.ApiResponse;
import com.technokratos.models.cards.CardProductDto;
import com.technokratos.services.CardService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cards")
public class CardsServlet extends HttpServlet {

    private CardService cardService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cardService = (CardService) config.getServletContext().getAttribute("cardService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiResponse<List<CardProductDto>> allCardsForChoose = cardService.getAllCardsForChoose();
        req.setAttribute("cardProducts", allCardsForChoose.getData());
        req.getRequestDispatcher("/jsp/cards.jsp").forward(req, resp);
    }

}
