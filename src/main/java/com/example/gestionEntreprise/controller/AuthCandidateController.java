package com.example.gestionEntreprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.service.AuthCandidateService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
// import com.example.gestionEntreprise.service.AuthCandidateService;

@Controller
public class AuthCandidateController {
    @Autowired
    private AuthCandidateService authCandidateService;

    @GetMapping("/candidate/auth")
    public String showLoginForm() {
        return "candidate/auth_candidate";
    }

    @GetMapping("/traitement_auth_Candidat")
    public String traitementAuthCandidate(String email, String password, 
        HttpSession session, RedirectAttributes redirectAttributes) {

        try {
            Candidate candidate = authCandidateService.findByEmail(email);

            if (candidate == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Email ou mot de passe incorrect");
                return "redirect:/auth_candidate";
            }

            if(!password.equals(candidate.getPassword())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Email ou mot de passe incorrect");
                return "redirect:/auth_candidate";
            }

            session.setAttribute("candidateId", candidate.getIdCandidate());
            session.setAttributee("personId", candidate.getIdPerson());
            session.setAttribute("candidateEmail", email);
            session.setAttribute("candidateName", candidate.getPerson().getFirstName() + " " + candidate.getPerson().getLastName());

            return "redirect:/candidature_form";

        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errir technique : " + e.getMessage());
            return "redirect:/auth_candidate";
        }

    }

    @PostMapping("")


}