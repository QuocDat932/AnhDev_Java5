package com.quocdat.java5.config;

import com.quocdat.java5.interceptor.AuthInterceptor;
import com.quocdat.java5.interceptor.ErrorLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private ErrorLoggingInterceptor errorLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .addPathPatterns("/admin/**")
                .addPathPatterns("/user/**")
                .excludePathPatterns("/login", "/index", "/");
        registry.addInterceptor(errorLoggingInterceptor);
    }
}
