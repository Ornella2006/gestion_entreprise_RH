package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class CandidateSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidateSkill;

    @ManyToOne
    @JoinColumn(name = "idCandidate")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "idSkill")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "idLevel")
    private Level level;

    // Getters and setters
    public Long getIdCandidateSkill() { return idCandidateSkill; }
    public void setIdCandidateSkill(Long idCandidateSkill) { this.idCandidateSkill = idCandidateSkill; }
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }
}
