package com.nefos.spring.jpa.h2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 1. Inject the allowed-origins list from application.properties
    @Value("${app.cors.allowed-origins}")
    private String[] allowedOrigins;

    // 2. Inject the max-age value
    @Value("${app.cors.max-age}")
    private long maxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // This applies the CORS configuration globally to ALL endpoints (/**)
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)        // Read from the property!
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")                   // Allow all headers
                .allowCredentials(true)                // Crucial for cookies/JWT in cookies
                .maxAge(maxAge);                       // Read from the property!
    }
}