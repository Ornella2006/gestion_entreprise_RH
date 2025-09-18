package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestTypeEnum testType;

    private Integer maxScore;

    @Temporal(TemporalType.DATE)
    private Date testDate;

    // Getters and setters
    public Long getIdTest() { return idTest; }
    public void setIdTest(Long idTest) { this.idTest = idTest; }
    public TestTypeEnum getTestType() { return testType; }
    public void setTestType(TestTypeEnum testType) { this.testType = testType; }
    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
    public Date getTestDate() { return testDate; }
    public void setTestDate(Date testDate) { this.testDate = testDate; }
}
