package com.example.gestionEntreprise.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        @RequestParam(required = false, defaultValue = "/candidate") String redirectTo,
        HttpSession session, RedirectAttributes redirectAttributes) {
    
    try {
         System.out.println("Tentative de connexion avec email: " + email);

        if (authCandidateService.authenticate(email, password)) {
             System.out.println("Authentification réussie pour: " + email);

            Candidate candidate = authCandidateService.getCandidateByEmail(email);
            Person person = candidate.getPerson();
            
            session.setAttribute("candidateId", candidate.getIdCandidate());
            session.setAttribute("personId", person.getIdPerson());
            session.setAttribute("candidateEmail", email);
            session.setAttribute("candidateName", person.getFirstName() + " " + person.getLastName());
            // session.setAttribute("candidatePhoto", person.getPhotoPath());
            
            session.setAttribute("candidateFirstName", person.getFirstName());
            session.setAttribute("candidateLastName", person.getLastName());
            session.setAttribute("candidatePhone", person.getPhone());

            System.out.println("Session créée pour: " + session.getAttribute("candidateName"));

            return "redirect:" + redirectTo;
        } else {
            System.out.println("Échec authentification pour: " + email);
            redirectAttributes.addFlashAttribute("errorMessage", "Email ou mot de passe incorrect");
             return "redirect:/auth_candidate?redirectTo=" + redirectTo;
        }
    } catch (Exception e) {
         System.out.println("Erreur technique: " + e.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", "Erreur technique : " + e.getMessage());
         return "redirect:/auth_candidate?redirectTo=" + redirectTo;
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

@GetMapping("/createTestAccount")
public String createTestAccount() {
    try {
        authCandidateService.registerCandidate(
            "test",          // firstName
            "user",          // lastName  
            "test@test.com", // email
            "1234",          // password
            "123456789",     // phone
            CandidateStatusType.other, // status
            null             // session
        );
        return "Compte test créé: test@test.com / 1234";
    } catch (Exception e) {
        return "Erreur: " + e.getMessage();
    }
}


 // Ajoutez cette méthode temporaire dans AuthCandidateController
    @GetMapping("/testAuth")
    public String testAuth() {
        String testEmail = "ornella@gmail.com";
        String testPassword = "1234";
        
        boolean result = authCandidateService.authenticate(testEmail, testPassword);
        System.out.println("Résultat test: " + result);
        
        return "Test terminé - résultat: " + result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/candidate";
    }

    @GetMapping("/debugSession")
    @ResponseBody
    public String debugSession(HttpSession session) {
        StringBuilder sb = new StringBuilder();
        sb.append("Session attributes:<br>");
        java.util.Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String name = attributes.nextElement();
            sb.append(name).append(": ").append(session.getAttribute(name)).append("<br>");
        }
        return sb.toString();
    }
}
