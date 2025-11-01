package com.technokratos.filters;

import com.technokratos.services.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class TokenFilter implements Filter {

    private TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        tokenService = (TokenService) filterConfig.getServletContext().getAttribute("tokenService");
    }

    // токен должен проверяться только на конкретные url
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (tokenService.isTokenAlive((String) request.getSession().getAttribute("token"))) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/sign-in");
        }

    }
}
