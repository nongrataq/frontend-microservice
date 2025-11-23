package com.technokratos.servlets;

import com.technokratos.models.ApiResponse;
import com.technokratos.services.cards.CardService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/cards/close")
public class CloseCardServlet extends HttpServlet {
    private CardService cardService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cardService = (CardService) config.getServletContext().getAttribute("cardService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID cardId = UUID.fromString(req.getParameter("cardId"));

        ApiResponse<Boolean> closed = cardService.closeCard(cardId);

        if (closed.getMessage().equals("success")) {
            //отобразить аттрибут или че то придумать чтоб понятно было что закрылась
            req.setAttribute("success","Карта успешно закрыта");
            resp.sendRedirect("%s/cards/info?%s".formatted(req.getContextPath(), cardId));
        }

    }
}
