package com.example.gestionEntreprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {
    // ... autowire repos si besoin ...
    @GetMapping("/test/qcm")
    public String showQcm(HttpSession session, Model model) {
        if (session.getAttribute("candidateId") == null) {
            return "redirect:/auth_candidate";
        }
        // Chargez questions QCM depuis BD (table Test_Question)
        // model.addAttribute("questions", testRepository.findAll());
        return "candidate/qcm"; // Créez qcm.html avec form pour réponses
    }

    @PostMapping("/submitQcm")
    public String submitQcm(HttpSession session, RedirectAttributes redirectAttributes) {
        // Calculez score, sauvez en BD (Candidate_Test)
        // Si score > seuil, status = passed, redirect to success
        return "redirect:/candidate";
    }
}
