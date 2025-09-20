package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "final_score")
public class FinalScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfinal_score")
    private Integer idFinalScore;

    @ManyToOne
    @JoinColumn(name = "idjob_application", referencedColumnName = "idjob_application")
    private JobApplication jobApplication;

    @Column(name = "test_score")
    private Integer testScore;

    @Column(name = "interview_score")
    private Integer interviewScore;

    @Column(name = "final_score", precision = 5, scale = 2)
    private BigDecimal finalScore;

    // Constructeurs
    public FinalScore() {
    }

    public FinalScore(JobApplication jobApplication, Integer testScore, 
                     Integer interviewScore, BigDecimal finalScore) {
        this.jobApplication = jobApplication;
        this.testScore = testScore;
        this.interviewScore = interviewScore;
        this.finalScore = finalScore;
    }

    // Getters et Setters
    public Integer getId() {
        return idFinalScore;
    }

    public void setId(Integer idFinalScore) {
        this.idFinalScore = idFinalScore;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public Integer getInterviewScore() {
        return interviewScore;
    }

    public void setInterviewScore(Integer interviewScore) {
        this.interviewScore = interviewScore;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "FinalScore{" +
                "idFinalScore=" + idFinalScore +
                ", jobApplication=" + jobApplication +
                ", testScore=" + testScore +
                ", interviewScore=" + interviewScore +
                ", finalScore=" + finalScore +
                '}';
    }
}