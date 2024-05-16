package com.service.student.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Add the allowed origin(s) here
                .allowedMethods("*") // Add the allowed HTTP methods here
                .allowedHeaders("*"); // Add the allowed headers here
    }
}
