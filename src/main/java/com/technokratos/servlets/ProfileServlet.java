package com.technokratos.servlets;

import com.technokratos.models.cards.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.services.CardService;
import com.technokratos.util.ServletUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private CardService cardService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cardService = (CardService) config.getServletContext().getAttribute("cardService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ApiResponse<List<CardDto>> userCards = cardService.getUserCards(ServletUtil.getUserId(session));

        req.setAttribute("userCards", userCards);
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}
