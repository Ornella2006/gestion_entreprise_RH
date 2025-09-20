package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_announcement")
public class JobAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjob_announcement")
    private Integer idJobAnnouncement;

    @ManyToOne
    @JoinColumn(name = "idrecruitment_request", referencedColumnName = "idrecruitment_request")
    private RecruitmentRequest recruitmentRequest;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @Column(name = "preview_link", length = 255)
    private String previewLink;

    // Constructeurs
    public JobAnnouncement() {
    }

    public JobAnnouncement(RecruitmentRequest recruitmentRequest, String description, 
                          LocalDate publicationDate, LocalDate closingDate, 
                          StatusType status, String previewLink) {
        this.recruitmentRequest = recruitmentRequest;
        this.description = description;
        this.publicationDate = publicationDate;
        this.closingDate = closingDate;
        this.status = status;
        this.previewLink = previewLink;
    }

    // Getters et Setters
    public Integer getId() {
        return idJobAnnouncement;
    }

    public void setId(Integer idJobAnnouncement) {
        this.idJobAnnouncement = idJobAnnouncement;
    }

    public RecruitmentRequest getRecruitmentRequest() {
        return recruitmentRequest;
    }

    public void setRecruitmentRequest(RecruitmentRequest recruitmentRequest) {
        this.recruitmentRequest = recruitmentRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "JobAnnouncement{" +
                "idJobAnnouncement=" + idJobAnnouncement +
                ", recruitmentRequest=" + recruitmentRequest +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                ", closingDate=" + closingDate +
                ", status=" + status +
                ", previewLink='" + previewLink + '\'' +
                '}';
    }
}