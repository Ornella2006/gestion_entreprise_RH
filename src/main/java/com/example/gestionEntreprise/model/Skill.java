package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;

    @Column(nullable = false, length = 100)
    private String skillName;

    // Getters and setters
    // ...
}
