package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class ProbationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProbationStatus;

    @Column(nullable = false, length = 50)
    private String nameStatus;

    // Getters and setters
    public Long getIdProbationStatus() { return idProbationStatus; }
    public void setIdProbationStatus(Long idProbationStatus) { this.idProbationStatus = idProbationStatus; }
    public String getNameStatus() { return nameStatus; }
    public void setNameStatus(String nameStatus) { this.nameStatus = nameStatus; }
}
