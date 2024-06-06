package com.quocdat.java5.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/admin")) {
            HttpSession httpSession = request.getSession();
            if (httpSession == null || !httpSession.getAttribute("user").equals("Admin")) {
                response.sendRedirect("/login");
                return false;
            }
        }
        if (requestURI.startsWith("/user")) {
            HttpSession httpSession = request.getSession();
            if (httpSession == null || !httpSession.getAttribute("user").equals("User")) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
