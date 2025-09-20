package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test_question")
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtest_question")
    private Integer idTestQuestion;

    @ManyToOne
    @JoinColumn(name = "idtest", referencedColumnName = "idtest")
    private Test test;

    @Column(name = "question_text", columnDefinition = "TEXT")
    private String questionText;

    // Constructeurs
    public TestQuestion() {
    }

    public TestQuestion(Test test, String questionText) {
        this.test = test;
        this.questionText = questionText;
    }

    // Getters et Setters
    public Integer getIdTestQuestion() {
        return idTestQuestion;
    }

    public void setIdTestQuestion(Integer idTestQuestion) {
        this.idTestQuestion = idTestQuestion;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "TestQuestion{" +
                "idTestQuestion=" + idTestQuestion +
                ", test=" + test +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}