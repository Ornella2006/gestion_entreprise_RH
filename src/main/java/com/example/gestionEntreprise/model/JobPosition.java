package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobPosition;

    @Column(nullable = false, length = 100)
    private String title;

    // Getters and setters
    public Long getIdJobPosition() { return idJobPosition; }
    public void setIdJobPosition(Long idJobPosition) { this.idJobPosition = idJobPosition; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
