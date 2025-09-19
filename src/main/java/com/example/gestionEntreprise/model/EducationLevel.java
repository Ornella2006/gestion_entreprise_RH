package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "education_level")
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEducation_level")
    private Integer idEducationLevel;

    @Column(nullable = false, length = 100,name = "level_name")
    private String levelName;


    // Getters and setters
    // ...
}
