package com.example.gestionEntreprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class JobApplicationController {


   @GetMapping("/demandeCandidature")
    public String demandeCandidature(HttpSession session) {
        if (session.getAttribute("candidateId") == null) {
            return "redirect:/auth_candidate?redirectTo=/demandeCandidature";
        }
        return "candidate/candidature_form";
    }

   
}
