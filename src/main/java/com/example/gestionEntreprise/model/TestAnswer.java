package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test_answer")
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtest_answer")
    private Integer idTestAnswer;

    @ManyToOne
    @JoinColumn(name = "idtest_question", referencedColumnName = "idtest_question")
    private TestQuestion testQuestion;

    @Column(name = "answer_text", columnDefinition = "TEXT")
    private String answerText;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "score")
    private Integer score;

    // Constructeurs
    public TestAnswer() {
    }

    public TestAnswer(TestQuestion testQuestion, String answerText, Boolean isCorrect, Integer score) {
        this.testQuestion = testQuestion;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.score = score;
    }

    // Getters et Setters
    public Integer getIdTestAnswer() {
        return idTestAnswer;
    }

    public void setIdTestAnswer(Integer idTestAnswer) {
        this.idTestAnswer = idTestAnswer;
    }

    public TestQuestion getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(TestQuestion testQuestion) {
        this.testQuestion = testQuestion;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "TestAnswer{" +
                "idTestAnswer=" + idTestAnswer +
                ", testQuestion=" + testQuestion +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                ", score=" + score +
                '}';
    }
}