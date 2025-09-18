package com.example.gestionEntreprise.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestionEntreprise.model.User;
import com.example.gestionEntreprise.service.UserService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class CompanyController {
    @Autowired
    private UserService userService;

    @GetMapping("/companyLogin")
    public String redirectToLoginCompany(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            return "redirect:/company/login?error=true";
        }
        return "redirect:/company/login";
    }
    @GetMapping("/company/login")
    public String companyLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "company/companyLogin";
    }

    @PostMapping("/company/login")
    public String handleCompanyLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // Logique d'authentification
        User user = userService.findLoginUser(username, password);
        if (user != null) {
            session.setAttribute("userId", user.getIdUser());
            return "redirect:/company/dashboard"; 
        } else {
            return "redirect:/company/login?error=true";
        }

    }
    @GetMapping("/company/dashboard")
    public String companyDashboard() {
        return "company/dashboard"; // Rends le template Thymeleaf
    }


}