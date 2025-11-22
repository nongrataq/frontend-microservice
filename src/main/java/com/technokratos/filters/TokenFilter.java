package com.technokratos.filters;

import com.technokratos.models.BlockType;
import com.technokratos.services.TokenService;
import com.technokratos.services.UserRestrictionsService;
import com.technokratos.util.ServletUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

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

        HttpSession session = request.getSession(false);

        String contextPath = request.getContextPath();
        String fullPath = request.getRequestURI();
        String path = fullPath.substring(contextPath.length());

        String token = (String) session.getAttribute("token");

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
