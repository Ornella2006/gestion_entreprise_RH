package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "final_selection")
public class FinalSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfinal_selection")
    private Integer idFinalSelection;

    @ManyToOne
    @JoinColumn(name = "idjob_application", referencedColumnName = "idjob_application")
    private JobApplication jobApplication;

    @Column(name = "decision_date")
    private LocalDate decisionDate;

    @Column(name = "notification_sent")
    private Boolean notificationSent = false;

    // Constructeurs
    public FinalSelection() {
    }

    public FinalSelection(JobApplication jobApplication, LocalDate decisionDate, Boolean notificationSent) {
        this.jobApplication = jobApplication;
        this.decisionDate = decisionDate;
        this.notificationSent = notificationSent;
    }

    // Getters et Setters
    public Integer getId() {
        return idFinalSelection;
    }

    public void setId(Integer idFinalSelection) {
        this.idFinalSelection = idFinalSelection;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public LocalDate getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDate decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Boolean getNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(Boolean notificationSent) {
        this.notificationSent = notificationSent;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "FinalSelection{" +
                "idFinalSelection=" + idFinalSelection +
                ", jobApplication=" + jobApplication +
                ", decisionDate=" + decisionDate +
                ", notificationSent=" + notificationSent +
                '}';
    }
}