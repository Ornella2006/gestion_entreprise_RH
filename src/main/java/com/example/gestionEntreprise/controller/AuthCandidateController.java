package com.example.gestionEntreprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthCandidateController {

    @GetMapping("/candidate/auth")
    public String showLoginForm() {
        return "candidate/auth_candidate";
    }
}