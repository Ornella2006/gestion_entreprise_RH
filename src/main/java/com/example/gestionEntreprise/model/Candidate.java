package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidate;

    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @Column(length = 50)
    private String currentStatus;

    @ManyToOne
    @JoinColumn(name = "idEducation_Level")
    private EducationLevel educationLevel;

    @ManyToOne
    @JoinColumn(name = "idLast_Degree")
    private Degree lastDegree;

    @ManyToOne
    @JoinColumn(name = "idSkill_Level")
    private SkillLevel skillLevel;

    @ManyToOne
    @JoinColumn(name = "idLanguage_Level")
    private LanguageLevel languageLevel;

    @Column(length = 50)
    private String expectedSalary;

    @Column(columnDefinition = "TEXT")
    private String additionalInfo;

    // Getters and setters
    // ...
}
