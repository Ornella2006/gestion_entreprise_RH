package com.example.gestionEntreprise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcandidate_skill")
    private Integer idCandidateSkill; 

    @ManyToOne
    @JoinColumn(name = "idcandidate")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "idskill")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "idlevel")
    private Level level;

    // Getters and setters
    public Integer getIdCandidateSkill() { return idCandidateSkill; }
    public void setIdCandidateSkill(Integer idCandidateSkill) { this.idCandidateSkill = idCandidateSkill; }
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }
}