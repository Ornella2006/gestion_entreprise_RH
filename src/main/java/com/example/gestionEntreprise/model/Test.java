package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtest")
    private Integer idTest;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type")
    private TestTypeEnum testType;

    @Column(name = "max_score")
    private Integer maxScore;

    @Column(name = "test_date")
    private LocalDate testDate;

    // Constructeurs
    public Test() {
    }

    public Test(TestTypeEnum testType, Integer maxScore, LocalDate testDate) {
        this.testType = testType;
        this.maxScore = maxScore;
        this.testDate = testDate;
    }

    // Getters et Setters
    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public TestTypeEnum getTestType() {
        return testType;
    }

    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Test{" +
                "idTest=" + idTest +
                ", testType=" + testType +
                ", maxScore=" + maxScore +
                ", testDate=" + testDate +
                '}';
    }
}