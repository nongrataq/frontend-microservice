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
        RequestSignInUserDto requestSignInUserDto = RequestSignInUserDto.builder()
                .phone(req.getParameter("phone"))
                .password(req.getParameter("password"))
                .build();

        ResponseSignInUserDto responseSignInUserDto = signInService.signIn(requestSignInUserDto);

        if (!responseSignInUserDto.isSuccess()) {
            req.getSession().setAttribute("errors", responseSignInUserDto.getErrors());
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        } else {
            req.getSession().setAttribute("token", responseSignInUserDto.getToken());
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
}