package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobApplication;

    @ManyToOne
    @JoinColumn(name = "idCandidate")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "idJob_Announcement")
    private JobAnnouncement jobAnnouncement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

    @Temporal(TemporalType.DATE)
    private Date applicationDate;

    // Getters and setters
    public Long getIdJobApplication() { return idJobApplication; }
    public void setIdJobApplication(Long idJobApplication) { this.idJobApplication = idJobApplication; }
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
    public JobAnnouncement getJobAnnouncement() { return jobAnnouncement; }
    public void setJobAnnouncement(JobAnnouncement jobAnnouncement) { this.jobAnnouncement = jobAnnouncement; }
    public StatusType getStatus() { return status; }
    public void setStatus(StatusType status) { this.status = status; }
    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }
}
