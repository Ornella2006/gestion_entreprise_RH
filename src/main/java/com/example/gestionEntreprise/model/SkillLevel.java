package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class SkillLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkillLevel;

    @ManyToOne
    @JoinColumn(name = "idSkill")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "idLevel")
    private Level level;

    // Getters and setters
    // ...
}
