package com.example.nexosapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll() // Permite todas las peticiones sin autenticación
                .and().csrf(csrf -> csrf.disable()); // Opcional: desactiva CSRF si no es necesario

        return http.build();
    }
}
