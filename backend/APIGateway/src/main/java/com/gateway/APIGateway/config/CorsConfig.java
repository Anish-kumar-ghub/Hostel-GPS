package com.gateway.APIGateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
public class CorsConfig implements WebFluxConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Add the allowed origin(s) here
                .allowedMethods("*") // Add the allowed HTTP methods here
                .allowedHeaders("*");// Add the allowed headers here
//                .allowCredentials(true);
    }
}
