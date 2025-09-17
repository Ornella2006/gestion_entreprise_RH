package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AnnouncementValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnouncementValidation;

    @ManyToOne
    @JoinColumn(name = "idJob_Announcement")
    private JobAnnouncement jobAnnouncement;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idDecision")
    private DecisionType decisionType;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validationDate;

    // Getters and setters
    // ...
}
