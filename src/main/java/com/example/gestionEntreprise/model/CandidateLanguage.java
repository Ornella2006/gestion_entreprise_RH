package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class CandidateLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidateLanguage;

    @ManyToOne
    @JoinColumn(name = "idCandidate")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "idLanguage")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "idLevel")
    private Level level;

    // Getters and setters
    public Long getIdCandidateLanguage() { return idCandidateLanguage; }
    public void setIdCandidateLanguage(Long idCandidateLanguage) { this.idCandidateLanguage = idCandidateLanguage; }
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }
}
