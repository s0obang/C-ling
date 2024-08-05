package com.example.cling.config;

import com.example.cling.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
