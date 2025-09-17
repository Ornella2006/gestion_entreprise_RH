package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class FormationExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormationExperience;

    @Column(length = 100)
    private String companyName;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Boolean isExperience = true;

    // Getters and setters
    // ...
}
