package com.technokratos.servlets;

import com.technokratos.models.users.FieldErrorDto;
import com.technokratos.models.users.RequestSignInUserDto;
import com.technokratos.models.users.ResponseSignInUserDto;
import com.technokratos.services.user_data.AuthService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        authService = (AuthService) config.getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("errors", session.getAttribute("errors"));
        session.removeAttribute("errors");
        req.getRequestDispatcher("/jsp/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        RequestSignInUserDto requestSignInUserDto = RequestSignInUserDto.builder()
                .phone(req.getParameter("phone"))
                .password(req.getParameter("password"))
                .build();

        try {
            ResponseSignInUserDto response = authService.signIn(requestSignInUserDto);

            log.info("Sign in response: {}", response);

            if (!response.isSuccess()) {
                session.setAttribute("errors", response.getErrors());
                resp.sendRedirect(req.getContextPath() + "/sign-in");
            } else {
                session.setAttribute("token", response.getToken());
                resp.sendRedirect(req.getContextPath() + "/profile");
            }
        } catch (Exception e) {
            log.error("Sign in error", e);
            FieldErrorDto error = FieldErrorDto.builder()
                    .type("SYSTEM_ERROR")
                    .message("Service temporarily unavailable")
                    .build();
            session.setAttribute("errors", List.of(error));
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        }
    }
}