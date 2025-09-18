package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class TestAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTestAnswer;

    @ManyToOne
    @JoinColumn(name = "idTestQuestion")
    private TestQuestion testQuestion;

    @Column(columnDefinition = "TEXT")
    private String answerText;

    private Boolean isCorrect;

    private Integer score;

    // Getters and setters
    public Long getIdTestAnswer() { return idTestAnswer; }
    public void setIdTestAnswer(Long idTestAnswer) { this.idTestAnswer = idTestAnswer; }
    public TestQuestion getTestQuestion() { return testQuestion; }
    public void setTestQuestion(TestQuestion testQuestion) { this.testQuestion = testQuestion; }
    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }
    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
