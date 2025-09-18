package com.example.gestionEntreprise.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobApplicationController {

    @GetMapping("/demandeCandidature")
    public String showFormJobApplication() {
        return "candidate/candidature_form";
    }
}