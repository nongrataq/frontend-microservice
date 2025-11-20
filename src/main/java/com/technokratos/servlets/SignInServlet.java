package com.technokratos.servlets;

import com.technokratos.models.RequestSignInUserDto;
import com.technokratos.models.ResponseSignInUserDto;
import com.technokratos.services.SignInService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", req.getAttribute("errors"));
        req.getRequestDispatcher("/jsp/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        RequestSignInUserDto requestSignInUserDto = RequestSignInUserDto.builder()
                .phone(req.getParameter("phone"))
                .password(req.getParameter("password"))
                .build();

        ResponseSignInUserDto response = signInService.signIn(requestSignInUserDto);

        if (!response.isSuccess()) {
            session.setAttribute("errors", response.getErrors());
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        } else {
            session.setAttribute("token", response.getToken());
            session.setAttribute("userId", response.getUserId());
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
}