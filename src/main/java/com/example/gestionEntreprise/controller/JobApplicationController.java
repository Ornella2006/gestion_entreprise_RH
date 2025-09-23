package com.example.gestionEntreprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.repository.CandidateRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class JobApplicationController {

    @Autowired
    private CandidateRepository candidateRepository; 

    @GetMapping("/demandeCandidature")
    public String demandeCandidature(HttpSession session, Model model) {
        if (session.getAttribute("candidateId") == null) {
            System.out.println("Accès non autorisé à /demandeCandidature - redirection");
            return "redirect:/auth_candidate?redirectTo=/demandeCandidature";
        }
        
        // Ajoutez les données nécessaires au modèle
        Integer candidateId = (Integer) session.getAttribute("candidateId");
         System.out.println("Candidate ID from session: " + candidateId);

        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        
        if (candidate != null && candidate.getPerson() != null) {
            Person person = candidate.getPerson();
            model.addAttribute("candidateFirstName", person.getFirstName());
            model.addAttribute("candidateLastName", person.getLastName());
            model.addAttribute("candidateEmail", person.getEmail());
            model.addAttribute("candidatePhone", person.getPhone());             
            System.out.println("Pré-remplissage avec: " + person.getFirstName() + " " + person.getLastName() + " - " + person.getEmail());
        } else {
            System.out.println("Candidat ou Person non trouvé pour ID: " + candidateId);
        }   
        
        return "candidate/candidature_form";
    }

    

    /* @GetMapping("/debugSession")
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
    } */
}


