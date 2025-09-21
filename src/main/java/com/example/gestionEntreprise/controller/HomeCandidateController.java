package com.example.gestionEntreprise.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeCandidateController {
     
    @GetMapping("/candidate")
    public String accueil_candidate(HttpSession session, Model model) {
        if (session.getAttribute("candidateName") != null) {
            model.addAttribute("candidateName", session.getAttribute("candidateName"));
            model.addAttribute("candidateEmail", session.getAttribute("candidateEmail"));
            model.addAttribute("candidatePhoto", session.getAttribute("candidatePhoto"));
        }
        return "candidate/accueil";
    }
}
