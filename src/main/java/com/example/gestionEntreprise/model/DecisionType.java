package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class DecisionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDecision;

    @Column(nullable = false, length = 50)
    private String nameDecision;

    // Getters and setters
    public Long getIdDecision() { return idDecision; }
    public void setIdDecision(Long idDecision) { this.idDecision = idDecision; }
    public String getNameDecision() { return nameDecision; }
    public void setNameDecision(String nameDecision) { this.nameDecision = nameDecision; }
}
