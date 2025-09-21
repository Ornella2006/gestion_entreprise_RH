package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "candidate") 
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcandidate")
    private Long idCandidate;

    @ManyToOne
    @JoinColumn(name = "idperson")
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", nullable = false)
    private CandidateStatusType currentStatus;

    @ManyToOne
    @JoinColumn(name = "ideducation_level")
    private EducationLevel educationLevel;

    @ManyToOne
    @JoinColumn(name = "idlast_degree")
    private Degree lastDegree;

    @Column(precision = 10, scale = 2,name = "expected_salary_min")
    private BigDecimal expectedSalaryMin;

    @Column(precision = 10, scale = 2,name = "expected_salary_max")
    private BigDecimal expectedSalaryMax;

    @Column(name = "additional_info", columnDefinition = "TEXT")
    private String additionalInfo;

    // Getters and setters
    public Long getIdCandidate() { return idCandidate; }
    public void setIdCandidate(Long idCandidate) { this.idCandidate = idCandidate; }
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
    public CandidateStatusType getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(CandidateStatusType currentStatus) { this.currentStatus = currentStatus; }
    public EducationLevel getEducationLevel() { return educationLevel; }
    public void setEducationLevel(EducationLevel educationLevel) { this.educationLevel = educationLevel; }
    public Degree getLastDegree() { return lastDegree; }
    public void setLastDegree(Degree lastDegree) { this.lastDegree = lastDegree; }
    public BigDecimal getExpectedSalaryMin() { return expectedSalaryMin; }
    public void setExpectedSalaryMin(BigDecimal expectedSalaryMin) { this.expectedSalaryMin = expectedSalaryMin; }
    public BigDecimal getExpectedSalaryMax() { return expectedSalaryMax; }
    public void setExpectedSalaryMax(BigDecimal expectedSalaryMax) { this.expectedSalaryMax = expectedSalaryMax; }
    public String getAdditionalInfo() { return additionalInfo; }
    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }
}
