package com.example.gestionEntreprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Désactive CSRF pour simplifier
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/candidate", "/css/**", "/js/**").permitAll()
                .requestMatchers("/demandeCandidature").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/candidate/auth")  // URL de ta page de login personnalisée
                .loginProcessingUrl("/candidate/auth")  // URL qui traite le login
                .defaultSuccessUrl("/candidate")
                .permitAll()
            );
        
        return http.build();
    }
}