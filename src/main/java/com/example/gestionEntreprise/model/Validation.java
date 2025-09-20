package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "validation")
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvalidation")
    private Integer idValidation;

    @ManyToOne
    @JoinColumn(name = "idrecruitment_request", referencedColumnName = "idrecruitment_request")
    private RecruitmentRequest recruitmentRequest;

    @ManyToOne
    @JoinColumn(name = "idjob_announcement", referencedColumnName = "idjob_announcement")
    private JobAnnouncement jobAnnouncement;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "decision")
    private StatusType decision;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    // Constructeurs
    public Validation() {
    }

    public Validation(RecruitmentRequest recruitmentRequest, JobAnnouncement jobAnnouncement, 
                     User user, StatusType decision, String comment, LocalDateTime validationDate) {
        this.recruitmentRequest = recruitmentRequest;
        this.jobAnnouncement = jobAnnouncement;
        this.user = user;
        this.decision = decision;
        this.comment = comment;
        this.validationDate = validationDate;
    }

    // Getters et Setters
    public Integer getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(Integer idValidation) {
        this.idValidation = idValidation;
    }

    public RecruitmentRequest getRecruitmentRequest() {
        return recruitmentRequest;
    }

    public void setRecruitmentRequest(RecruitmentRequest recruitmentRequest) {
        this.recruitmentRequest = recruitmentRequest;
    }

    public JobAnnouncement getJobAnnouncement() {
        return jobAnnouncement;
    }

    public void setJobAnnouncement(JobAnnouncement jobAnnouncement) {
        this.jobAnnouncement = jobAnnouncement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusType getDecision() {
        return decision;
    }

    public void setDecision(StatusType decision) {
        this.decision = decision;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDateTime validationDate) {
        this.validationDate = validationDate;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Validation{" +
                "idValidation=" + idValidation +
                ", recruitmentRequest=" + recruitmentRequest +
                ", jobAnnouncement=" + jobAnnouncement +
                ", user=" + user +
                ", decision=" + decision +
                ", comment='" + comment + '\'' +
                ", validationDate=" + validationDate +
                '}';
    }
}