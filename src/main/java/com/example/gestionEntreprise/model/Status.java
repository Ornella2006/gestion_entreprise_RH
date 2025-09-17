package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @Column(nullable = false, length = 50)
    private String nameStatus;

    // Getters and setters
    public Long getIdStatus() { return idStatus; }
    public void setIdStatus(Long idStatus) { this.idStatus = idStatus; }
    public String getNameStatus() { return nameStatus; }
    public void setNameStatus(String nameStatus) { this.nameStatus = nameStatus; }
}
