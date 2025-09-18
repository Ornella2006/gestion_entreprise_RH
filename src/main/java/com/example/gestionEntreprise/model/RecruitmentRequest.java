package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class RecruitmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecruitmentRequest;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idJob_Position")
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "idDepartment")
    private Department department;

    @Column(length = 50)
    private String needType;

    @Column(columnDefinition = "TEXT")
    private String justification;

    @Column(columnDefinition = "TEXT")
    private String mainMission;

    @Column(length = 50)
    private String contractType;

    @Column(length = 50)
    private String workingTime;

    @Column(length = 100)
    private String city;

    @Column(precision = 10, scale = 2)
    private BigDecimal salaryMin;

    @Column(precision = 10, scale = 2)
    private BigDecimal salaryMax;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

    // Getters and setters
    public Long getIdRecruitmentRequest() { return idRecruitmentRequest; }
    public void setIdRecruitmentRequest(Long idRecruitmentRequest) { this.idRecruitmentRequest = idRecruitmentRequest; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public JobPosition getJobPosition() { return jobPosition; }
    public void setJobPosition(JobPosition jobPosition) { this.jobPosition = jobPosition; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public String getNeedType() { return needType; }
    public void setNeedType(String needType) { this.needType = needType; }
    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }
    public String getMainMission() { return mainMission; }
    public void setMainMission(String mainMission) { this.mainMission = mainMission; }
    public String getContractType() { return contractType; }
    public void setContractType(String contractType) { this.contractType = contractType; }
    public String getWorkingTime() { return workingTime; }
    public void setWorkingTime(String workingTime) { this.workingTime = workingTime; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public BigDecimal getSalaryMin() { return salaryMin; }
    public void setSalaryMin(BigDecimal salaryMin) { this.salaryMin = salaryMin; }
    public BigDecimal getSalaryMax() { return salaryMax; }
    public void setSalaryMax(BigDecimal salaryMax) { this.salaryMax = salaryMax; }
    public StatusType getStatus() { return status; }
    public void setStatus(StatusType status) { this.status = status; }
}
