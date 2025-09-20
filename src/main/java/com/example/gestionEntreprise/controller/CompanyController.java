package com.example.gestionEntreprise.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestionEntreprise.model.User;
import com.example.gestionEntreprise.model.Role;
import com.example.gestionEntreprise.service.UserService;
import com.example.gestionEntreprise.service.EmployeeService;
import com.example.gestionEntreprise.service.RoleService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class CompanyController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/company")
    public String companyHome() {
        return "redirect:/company/login";
    }
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
            session.setAttribute("userRole", userService.getRoleNameByUserId(user.getIdUser()));
            return "redirect:/company/dashboard"; 
        } else {
            return "redirect:/company/login?error=true";
        }

    }
    @GetMapping("/company/dashboard")
    public String companyDashboard(HttpSession session,Model model) {
        
        if (session.getAttribute("userId") == null) {
            return "redirect:/company/login";
        }
        
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userRole", session.getAttribute("userRole"));
        model.addAttribute("employeeId", session.getAttribute("employeeId"));
        return "company/dashboard"; // Rends le template Thymeleaf
    }


}