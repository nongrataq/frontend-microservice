package com.technokratos.filters;

import com.technokratos.models.users.ResponseUserDto;
import com.technokratos.services.user_data.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {

    private AuthService authService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authService = (AuthService) filterConfig.getServletContext().getAttribute("authService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String contextPath = request.getContextPath();
        String fullPath = request.getRequestURI();
        String path = fullPath.substring(contextPath.length());

        UUID token = (UUID) session.getAttribute("token");

        if (path.equals("/sign-in") || path.equals("/sign-up") || path.equals("/")) {
            filterChain.doFilter(request, response);
            return;
        }


        ResponseUserDto responseUserDto = authService.validateToken(token);
        if (token != null && responseUserDto.isSuccess()) {
            filterChain.doFilter(request, response);
        } else {
            log.info(responseUserDto.getErrors().toString());
            response.sendRedirect(request.getContextPath() + "/sign-in");
        }

    }
}
