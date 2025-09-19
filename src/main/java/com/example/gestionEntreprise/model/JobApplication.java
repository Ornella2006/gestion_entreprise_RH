package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjob_application")
    private Integer idJobApplication;

    @ManyToOne
    @JoinColumn(name = "idcandidate", referencedColumnName = "idcandidate")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "idjob_announcement", referencedColumnName = "idjob_announcement")
    private JobAnnouncement jobAnnouncement;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    // Constructeurs
    public JobApplication() {
    }

    public JobApplication(Candidate candidate, JobAnnouncement jobAnnouncement, 
                         StatusType status, LocalDate applicationDate) {
        this.candidate = candidate;
        this.jobAnnouncement = jobAnnouncement;
        this.status = status;
        this.applicationDate = applicationDate;
    }

    // Getters et Setters
    public Integer getId() {
        return idJobApplication;
    }

    public void setId(Integer idJobApplication) {
        this.idJobApplication = idJobApplication;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public JobAnnouncement getJobAnnouncement() {
        return jobAnnouncement;
    }

    public void setJobAnnouncement(JobAnnouncement jobAnnouncement) {
        this.jobAnnouncement = jobAnnouncement;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "JobApplication{" +
                "idJobApplication=" + idJobApplication +
                ", candidate=" + candidate +
                ", jobAnnouncement=" + jobAnnouncement +
                ", status=" + status +
                ", applicationDate=" + applicationDate +
                '}';
    }
}