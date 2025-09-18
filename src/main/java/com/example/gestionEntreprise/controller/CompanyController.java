package com.example.gestionEntreprise.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {
    @GetMapping("/companyLogin")
    public String redirectToLoginCompany() {
        return "redirect:/company/login"; // Modifie l'URL cible pour plus de clarté
    }

    @GetMapping("/company/login")
    public String companyLogin() {
        return "company/companyLogin"; // Rends le template Thymeleaf
    }
}