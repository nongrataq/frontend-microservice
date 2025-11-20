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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath = request.getContextPath();
        String fullPath = request.getRequestURI();
        String path = fullPath.substring(contextPath.length());

        String token = (String) request.getSession().getAttribute("token");

        if (path.equals("/sign-in") || path.equals("/sign-up") || path.equals("/")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token != null && tokenService.isTokenAlive(token)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/sign-in");
        }

    }
}
