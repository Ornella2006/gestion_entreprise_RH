package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class RecruitmentCriteriaPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCriteriaPriority;

    @ManyToOne
    @JoinColumn(name = "idRecruitment_Request")
    private RecruitmentRequest recruitmentRequest;

    @Column(nullable = false, length = 50)
    private String criteriaType;

    @ManyToOne
    @JoinColumn(name = "criteriaType", referencedColumnName = "criteriaType", insertable = false, updatable = false)
    private CriteriaMapping criteriaMapping;

    @Column(length = 255)
    private String criteriaValue;

    @Column(length = 10)
    private String operator = "=";

    private Boolean isMandatory = true;

    // Getters and setters
    public Long getIdCriteriaPriority() { return idCriteriaPriority; }
    public void setIdCriteriaPriority(Long idCriteriaPriority) { this.idCriteriaPriority = idCriteriaPriority; }
    public RecruitmentRequest getRecruitmentRequest() { return recruitmentRequest; }
    public void setRecruitmentRequest(RecruitmentRequest recruitmentRequest) { this.recruitmentRequest = recruitmentRequest; }
    public String getCriteriaType() { return criteriaType; }
    public void setCriteriaType(String criteriaType) { this.criteriaType = criteriaType; }
    public CriteriaMapping getCriteriaMapping() { return criteriaMapping; }
    public void setCriteriaMapping(CriteriaMapping criteriaMapping) { this.criteriaMapping = criteriaMapping; }
    public String getCriteriaValue() { return criteriaValue; }
    public void setCriteriaValue(String criteriaValue) { this.criteriaValue = criteriaValue; }
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
    public Boolean getIsMandatory() { return isMandatory; }
    public void setIsMandatory(Boolean isMandatory) { this.isMandatory = isMandatory; }
}
