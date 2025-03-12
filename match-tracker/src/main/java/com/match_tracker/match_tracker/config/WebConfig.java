package com.match_tracker.match_tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Разрешает CORS для всех URL
                .allowedOrigins("http://localhost:4200", "http://localhost:3000")  // Разрешает запросы с фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Разрешает нужные методы
                .allowedHeaders("*")  // Разрешает все заголовки
                .allowCredentials(true);  // Разрешает отправку cookies
    }
}
