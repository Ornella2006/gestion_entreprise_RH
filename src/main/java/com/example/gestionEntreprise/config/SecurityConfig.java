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
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/candidate", "/css/**", "/js/**", 
                               "/auth_candidate", "/traitement_auth_Candidat", 
                               "/traitement_signup_Candidat").permitAll()
                .requestMatchers("/demandeCandidature", "/candidature_form").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/auth_candidate")  // URL de ta page de login personnalisée
                .loginProcessingUrl("/traitement_auth_Candidat")  // URL qui traite le login
                .defaultSuccessUrl("/candidature_form")
                .permitAll()
            );
        
        return http.build();
    }
}