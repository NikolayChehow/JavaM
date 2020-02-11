package com.javamentor.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class AuthFilterUser implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        if (request.getSession(false).getAttribute("name")==null){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            if (session != null && session.getAttribute("admin") == null)
                servletRequest.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        filterChain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
}
