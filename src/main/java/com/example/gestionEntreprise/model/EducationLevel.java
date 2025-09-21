package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "education_level")
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideducation_level")
    private Integer idEducationLevel; // Changé de Long à Integer

    @Column(name = "level_name", nullable = false, length = 100)
    private String levelName;

    // Getters and setters
    public Integer getIdEducationLevel() { return idEducationLevel; }
    public void setIdEducationLevel(Integer idEducationLevel) { this.idEducationLevel = idEducationLevel; }
    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }
}