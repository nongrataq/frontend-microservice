package com.technokratos.servlets;

import com.technokratos.models.users.FieldErrorDto;
import com.technokratos.models.users.RequestSignUpUserDto;
import com.technokratos.models.users.ResponseUserDto;
import com.technokratos.services.user_data.AuthService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        authService = (AuthService) config.getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<FieldErrorDto> errors = (List<FieldErrorDto>) session.getAttribute("errors");
        req.setAttribute("errors", errors);
        session.removeAttribute("errors");
        req.getRequestDispatcher("/jsp/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String fio = req.getParameter("fio");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        ResponseUserDto response = authService.signUp(RequestSignUpUserDto.builder()
                .fio(fio)
                .phone(phone)
                .password(password)
                .build()
        );

        if (response.isSuccess()) {
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        } else {
            session.setAttribute("errors", response.getErrors());
            resp.sendRedirect(req.getContextPath() + "/sign-up");
        }
    }
}
