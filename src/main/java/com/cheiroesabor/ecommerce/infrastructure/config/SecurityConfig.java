package com.cheiroesabor.ecommerce.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // DESATIVA o CSRF para permitir POST/PUT
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Mantém a necessidade de login
            )
            .httpBasic(Customizer.withDefaults()); // Mantém o Basic Auth que você usou no Insomnia
        
        return http.build();
    }
}