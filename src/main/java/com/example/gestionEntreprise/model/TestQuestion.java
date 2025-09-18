package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTestQuestion;

    @ManyToOne
    @JoinColumn(name = "idTest")
    private Test test;

    @Column(columnDefinition = "TEXT")
    private String questionText;

    // Getters and setters
    public Long getIdTestQuestion() { return idTestQuestion; }
    public void setIdTestQuestion(Long idTestQuestion) { this.idTestQuestion = idTestQuestion; }
    public Test getTest() { return test; }
    public void setTest(Test test) { this.test = test; }
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
}
