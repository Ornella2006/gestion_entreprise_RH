package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class JobAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobAnnouncement;

    @ManyToOne
    @JoinColumn(name = "idRecruitment_Request")
    private RecruitmentRequest recruitmentRequest;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Temporal(TemporalType.DATE)
    private Date closingDate;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;

    // Getters and setters
    // ...
}
