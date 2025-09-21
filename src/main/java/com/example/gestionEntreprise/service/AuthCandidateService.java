package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.*;
import com.example.gestionEntreprise.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthCandidateService {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private CandidateAuthRepository candidateAuthRepository;
    
    // SUPPRIMER l'injection de PasswordEncoder
    
    @Transactional
    public void registerCandidate(String firstName, String lastName, String email, 
                                String password, String phone, CandidateStatusType currentStatus, 
                                HttpSession session) {
        
        System.out.println("=== INSCRIPTION ===");
        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    

        if (personRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        
        if (candidateAuthRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        
        // Créer la Person
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setPhone(phone);
        person = personRepository.save(person);
        
        System.out.println("Person créée: " + person.getFirstName() + " " + person.getLastName());

        // Créer le Candidate
        Candidate candidate = new Candidate();
        candidate.setPerson(person);
        candidate.setCurrentStatus(currentStatus);
        candidate = candidateRepository.save(candidate);
        
        // Créer l'authentification (sans encoder le mot de passe)
        CandidateAuth candidateAuth = new CandidateAuth();
        candidateAuth.setCandidate(candidate);
        candidateAuth.setEmail(email);
        candidateAuth.setPassword(password); // ← Stockage en clair
        candidateAuth.setCreatedAt(LocalDateTime.now());
        candidateAuthRepository.save(candidateAuth);

          System.out.println("=== INSCRIPTION RÉUSSIE ===");
    }
    
    public boolean authenticate(String email, String password) {
        System.out.println("=== DÉBUT AUTHENTIFICATION ===");
        System.out.println("Email reçu: " + email);
        System.out.println("Password reçu: " + password);

        return candidateAuthRepository.findByEmail(email)
            .map(candidateAuth -> {
            System.out.println("CandidateAuth trouvé: " + candidateAuth.getEmail());
            System.out.println("Password en base: " + candidateAuth.getPassword());
            System.out.println("Email recherché: " + email);
            System.out.println("Password fourni: " + password);
            System.out.println("Password en base: " + candidateAuth.getPassword());
            
            // Comparaison simple sans hachage
            boolean result = password.equals(candidateAuth.getPassword());
            System.out.println("Résultat comparaison: " + result);
            System.out.println("=== FIN AUTHENTIFICATION ===");
            
            return result;
        })
        .orElseGet(() -> {
            System.out.println("Aucun CandidateAuth trouvé pour: " + email);
            System.out.println("=== FIN AUTHENTIFICATION ===");
            return false;
        });
    }

     public Candidate getCandidateByEmail(String email) {
        return candidateAuthRepository.findByEmail(email)
            .map(CandidateAuth::getCandidate)
            .orElse(null);
    }
}