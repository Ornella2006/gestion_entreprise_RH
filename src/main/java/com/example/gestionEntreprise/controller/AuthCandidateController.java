package com.example.gestionEntreprise.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.model.CandidateStatusType;
import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.repository.CandidateRepository;
import com.example.gestionEntreprise.repository.PersonRepository;
import com.example.gestionEntreprise.service.AuthCandidateService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthCandidateController {

    @Autowired
    private AuthCandidateService authCandidateService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/auth_candidate")
    public String showAuthCandidatePage(Model model) {
        return "candidate/auth_candidate";
    }

    @GetMapping("/candidature_form")
    public String showFormJobApplication(HttpSession session, Model model) {
        if (session.getAttribute("candidateId")== null) {
            return "redirect:/auth_candidate";
        }
        return "candidate/candidature_form";
    }

    @PostMapping("/traitement_auth_Candidat")
    public String traitementAuthCandidat(String email, String password, 
            HttpSession session, RedirectAttributes redirectAttributes) {
        
        try {
            // Recherche du candidat par email
            if (authCandidateService.authenticate(email, password, session)) {
                Person person = personRepository.findByEmail(email);
                Candidate candidate = candidateRepository.findByPersonId(person.getIdPerson());
            
            
                session.setAttribute("candidateId", candidate.getIdCandidate());
                session.setAttribute("personId", person.getIdPerson());
                session.setAttribute("candidateEmail", email);
                session.setAttribute("candidateName", 
                    person.getFirstName() + " " + person.getLastName());

                return "redirect:/candidature_form";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Email ou mot de passe incorrect");
                return "redirect:/auth_candidate";
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur technique : " + e.getMessage());
            return "redirect:/auth_candidate";
        }
    }

   @PostMapping("/traitement_signup_Candidat")
public String traitementSignupCandidat(
        @RequestParam String first_name, 
        @RequestParam String last_name, 
        @RequestParam String email, 
        @RequestParam String phone, 
        @RequestParam String current_status, // Changé en String
        @RequestParam String password, 
        HttpSession session,            
        RedirectAttributes redirectAttributes) {

    try {
        // Conversion du String en enum
        CandidateStatusType status;
        try {
            status = CandidateStatusType.valueOf(current_status.toUpperCase());
        } catch (IllegalArgumentException e) {
            status = CandidateStatusType.other;
        }
        
        authCandidateService.registerCandidate(first_name, last_name, email, password, phone, status, session);
        redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        return "redirect:/auth_candidate";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'inscription : " + e.getMessage());
        return "redirect:/auth_candidate";
    }
}




}