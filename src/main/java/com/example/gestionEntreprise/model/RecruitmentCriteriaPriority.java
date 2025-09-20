package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recruitment_criteria_priority")
public class RecruitmentCriteriaPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcriteria_priority")
    private Integer idCriteriaPriority;
    @ManyToOne
    @JoinColumn(name = "idrecruitment_request", referencedColumnName = "idrecruitment_request")
    private RecruitmentRequest recruitmentRequest;

    @Column(name = "criteria_type", nullable = false, length = 50)
    private String criteriaType;

    @Column(name = "criteria_value", length = 255)
    private String criteriaValue;

    @Column(name = "operator", length = 10)
    private String operator = "=";

    @Column(name = "is_mandatory")
    private Boolean isMandatory = true;

    // Constructeurs
    public RecruitmentCriteriaPriority() {
    }

    public RecruitmentCriteriaPriority(RecruitmentRequest recruitmentRequest, String criteriaType, 
                                     String criteriaValue, String operator, Boolean isMandatory) {
        this.recruitmentRequest = recruitmentRequest;
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        this.operator = operator;
        this.isMandatory = isMandatory;
    }

    // Getters et Setters
    public Integer getIdCriteriaPriority() {
        return idCriteriaPriority;
    }

    public void setIdCriteriaPriority(Integer idCriteriaPriority) {
        this.idCriteriaPriority = idCriteriaPriority;
    }

    public RecruitmentRequest getRecruitmentRequest() {
        return recruitmentRequest;
    }

    public void setRecruitmentRequest(RecruitmentRequest recruitmentRequest) {
        this.recruitmentRequest = recruitmentRequest;
    }

    public String getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(String criteriaType) {
        this.criteriaType = criteriaType;
    }

    public String getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(String criteriaValue) {
        this.criteriaValue = criteriaValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "RecruitmentCriteriaPriority{" +
                "idCriteriaPriority=" + idCriteriaPriority +
                ", recruitmentRequest=" + recruitmentRequest +
                ", criteriaType='" + criteriaType + '\'' +
                ", criteriaValue='" + criteriaValue + '\'' +
                ", operator='" + operator + '\'' +
                ", isMandatory=" + isMandatory +
                '}';
    }
}