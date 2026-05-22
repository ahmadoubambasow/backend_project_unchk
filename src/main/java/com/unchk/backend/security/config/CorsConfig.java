package com.unchk.backend.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Configuration CORS globale.
 */
@Configuration
public class CorsConfig {

    /**
     * Configuration des accès frontend ↔ backend.
     */
    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config =
                new CorsConfiguration();

        // Frontend Angular autorisé
        config.setAllowedOrigins(
                List.of("http://localhost:4200")
        );

        // Headers autorisés
        config.setAllowedHeaders(
                List.of("*")
        );

        // Méthodes HTTP autorisées
        config.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        // Autorise Authorization Bearer JWT
        config.setAllowCredentials(true);

        // Expose certains headers
        config.setExposedHeaders(
                List.of("Authorization")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                config
        );

        return new CorsFilter(source);
    }
}