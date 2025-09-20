package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "recruitment_request")
public class RecruitmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecruitment_request")
    private Integer idRecruitmentRequest;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idjob_position", referencedColumnName = "idjob_position")
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "iddepartment", referencedColumnName = "iddepartment")
    private Department department;

    @Column(name = "need_type", length = 50)
    private String needType;

    @Column(name = "justification", columnDefinition = "TEXT")
    private String justification;

    @Column(name = "main_mission", columnDefinition = "TEXT")
    private String mainMission;

    @Column(name = "contract_type", length = 50)
    private String contractType;

    @Column(name = "working_time", length = 50)
    private String workingTime;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "salary_min", precision = 10, scale = 2)
    private BigDecimal salaryMin;

    @Column(name = "salary_max", precision = 10, scale = 2)
    private BigDecimal salaryMax;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    // Constructeurs
    public RecruitmentRequest() {
    }

    public RecruitmentRequest(User user, JobPosition jobPosition, Department department, 
                            String needType, String justification, String mainMission, 
                            String contractType, String workingTime, String city, 
                            BigDecimal salaryMin, BigDecimal salaryMax, StatusType status) {
        this.user = user;
        this.jobPosition = jobPosition;
        this.department = department;
        this.needType = needType;
        this.justification = justification;
        this.mainMission = mainMission;
        this.contractType = contractType;
        this.workingTime = workingTime;
        this.city = city;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.status = status;
    }

    // Getters et Setters
    public Integer getIdRecruitmentRequest() {
        return idRecruitmentRequest;
    }

    public void setIdRecruitmentRequest(Integer idRecruitmentRequest) {
        this.idRecruitmentRequest = idRecruitmentRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNeedType() {
        return needType;
    }

    public void setNeedType(String needType) {
        this.needType = needType;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getMainMission() {
        return mainMission;
    }

    public void setMainMission(String mainMission) {
        this.mainMission = mainMission;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(BigDecimal salaryMin) {
        this.salaryMin = salaryMin;
    }

    public BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(BigDecimal salaryMax) {
        this.salaryMax = salaryMax;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "RecruitmentRequest{" +
                "idRecruitmentRequest=" + idRecruitmentRequest +
                ", user=" + user +
                ", jobPosition=" + jobPosition +
                ", department=" + department +
                ", needType='" + needType + '\'' +
                ", justification='" + justification + '\'' +
                ", mainMission='" + mainMission + '\'' +
                ", contractType='" + contractType + '\'' +
                ", workingTime='" + workingTime + '\'' +
                ", city='" + city + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", status=" + status +
                '}';
    }
}