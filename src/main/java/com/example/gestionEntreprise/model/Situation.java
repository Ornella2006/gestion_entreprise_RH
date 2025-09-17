package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Situation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSituation;

    @Column(nullable = false, length = 100)
    private String nameSituation;

    // Getters and setters
    public Long getIdSituation() { return idSituation; }
    public void setIdSituation(Long idSituation) { this.idSituation = idSituation; }
    public String getNameSituation() { return nameSituation; }
    public void setNameSituation(String nameSituation) { this.nameSituation = nameSituation; }
}
