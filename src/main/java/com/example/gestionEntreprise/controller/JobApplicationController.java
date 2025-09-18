/* package com.example.gestionEntreprise.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobApplicationController {

    @GetMapping("/demandeCandidature")
    public String showFormJobApplication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            // Redirige vers la page de login
            return "redirect:/login";
        }
        // Si connecté, affiche le formulaire
        return "candidate/candidature_form";
    }
} */