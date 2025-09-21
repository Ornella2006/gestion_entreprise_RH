package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "education_level")
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideducation_level")
    private Long idEducationLevel; // Changé de Integer à Long

    @Column(name = "level_name", nullable = false, length = 100)
    private String levelName;

    // Getters and setters
    public Long getIdEducationLevel() { return idEducationLevel; }
    public void setIdEducationLevel(Long idEducationLevel) { this.idEducationLevel = idEducationLevel; }
    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }
}