package com.example.gestionEntreprise.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.UUID;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.gestionEntreprise.model.*;
import com.example.gestionEntreprise.repository.*;


import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {

    private CandidateRepository candidateRepository;
    private PersonRepository personRepository;


    @PostMapping("/uploadPhoto")
public String uploadPhoto(@RequestParam("photo") MultipartFile file, 
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
    try {
        if (session.getAttribute("candidateId") == null) {
            return "redirect:/auth_candidate";
        }
        
        Integer candidateId = (Integer) session.getAttribute("candidateId");
        Candidate candidate = candidateRepository.findById(candidateId.longValue()).orElse(null);
        
        if (candidate != null) {
            // Créer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // Chemin où sauvegarder l'image
            Path path = Paths.get("uploads/" + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            
            // Mettre à jour le chemin dans la base de données
            Person person = candidate.getPerson();
            person.setPhotoPath("/uploads/" + fileName);
            personRepository.save(person);
            
            // Mettre à jour la session
            session.setAttribute("candidatePhoto", "/uploads/" + fileName);
            
            redirectAttributes.addFlashAttribute("successMessage", "Photo téléchargée avec succès");
        }
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors du téléchargement: " + e.getMessage());
    }
    
    return "redirect:/demandeCandidature";
}
}