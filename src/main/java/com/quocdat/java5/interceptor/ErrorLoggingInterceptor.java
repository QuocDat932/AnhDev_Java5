package com.quocdat.java5.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Component
public class ErrorLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLoggingInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            logger.error("Exception occurred in request: {} with handler {}.", request.getRequestURL(), handler);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            logger.error("Internal server error occurred in request: {} with handler {}.", request.getRequestURL(), handler);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Received request: {} with handler {}.", request.getRequestURL(), handler);
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables != null && !pathVariables.isEmpty()) {
            logger.info("Path variables: {}.", pathVariables);
        }
        HttpSession session = request.getSession();
        Enumeration<String> attributeNamesEnum = session.getAttributeNames();
        List<String> attributeNamesList = new ArrayList<>();

        while (attributeNamesEnum.hasMoreElements()) {
            attributeNamesList.add(attributeNamesEnum.nextElement());
        }

        if (session != null) {
            logger.info("Session variables: {}.", attributeNamesList.toArray(new String[0]));
        }
        return true;
    }
}
