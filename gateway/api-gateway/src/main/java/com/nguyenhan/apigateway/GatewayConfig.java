package com.nguyenhan.apigateway;


import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {
    @Bean
    public GlobalCorsProperties globalCorsProperties() {
        return new GlobalCorsProperties();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        // Chỉ định rõ origin thay vì "*"
//        corsConfig.addAllowedOrigin("http://localhost:3000");
//        corsConfig.addAllowedMethod("*"); // Cho phép tất cả các phương thức (GET, POST, v.v.)
//        corsConfig.addAllowedHeader("*"); // Cho phép tất cả các header
//        corsConfig.setAllowCredentials(true); // Cho phép gửi cookie/credentials
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//        return source;
//    }
}