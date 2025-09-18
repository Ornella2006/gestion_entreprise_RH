package com.example.gestionEntreprise.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCandidateController {
     
    @GetMapping("/candidate")
    public String accueil_candidate() {
        return "candidate/accueil";
    }
}
