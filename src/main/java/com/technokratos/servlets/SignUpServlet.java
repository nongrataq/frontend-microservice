package com.technokratos.servlets;

import com.technokratos.models.RequestSignUpUserDto;
import com.technokratos.models.ResponseSignUpUserDto;
import com.technokratos.services.SignUpService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String fio = req.getParameter("fio");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        ResponseSignUpUserDto responseSignUpUserDto = signUpService.signUp(RequestSignUpUserDto.builder()
                .fio(fio)
                .phone(phone)
                .password(password)
                .build()
        );

        if (responseSignUpUserDto.isSuccess()) {
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        } else {
            session.setAttribute("errors", responseSignUpUserDto.getErrors());
            resp.sendRedirect(req.getContextPath() + "/sign-up");
        }
    }
}
