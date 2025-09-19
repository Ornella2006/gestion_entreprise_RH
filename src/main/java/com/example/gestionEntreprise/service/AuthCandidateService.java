package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.repository.CandidateRepository;
import com.example.gestionEntreprise.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestionEntreprise.model.CandidateStatusType;

@Service
public class AuthCandidateService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate findByEmail(String email) {
        return candidateRepository.findByPersonEmail(email);
    }

    public boolean emailExists(String email) {
        return personRepository.existsByEmail(email);
    }

    public boolean authenticate(String email, String password, HttpSession session) {
        Person person = personRepository.findByEmail(email);
        if (person == null) return false;

        Candidate candidate = candidateRepository.findByPersonId(person.getIdPerson());
        if (candidate == null) return false;

        String sessionPassword = (String) session.getAttribute("pwd_" + email);
        if (sessionPassword == null) return false;

        // Comparaison simple des mots de passe sans encodage
        return password.equals(sessionPassword);
    }

    @Transactional
    public void registerCandidate(String firstName, String lastName, String email, 
                                 String password, String phone, CandidateStatusType currentStatus, 
                                 HttpSession session) {
        if (personRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setPhone(phone);
        personRepository.save(person);

        Candidate candidate = new Candidate();
        candidate.setPerson(person);
        candidate.setCurrentStatus(currentStatus);
        candidateRepository.save(candidate);

        // Stocker le mot de passe en clair dans la session
        session.setAttribute("pwd_" + email, password);
    }

    @Transactional
    public void saveCandidate(Candidate candidate) {
        Person person = personRepository.save(candidate.getPerson());
        candidate.setPerson(person);
        candidateRepository.save(candidate);
    }
}