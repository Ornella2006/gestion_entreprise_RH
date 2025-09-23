package com.example.gestionEntreprise.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CandidatureForm {
    private MultipartFile photo;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String email;
    private String phone;
    private String city;
    private String linkedin;
    private String maritalStatus;
    private Boolean driverLicense;
    private String currentStatus;
    private Integer educationLevel;
    private Integer lastDegree;
    private Double salaryMin;
    private Double salaryMax;
    private List<String> companyName = new ArrayList<>();
    private List<String> position = new ArrayList<>();
    private List<String> startDate = new ArrayList<>();
    private List<String> endDate = new ArrayList<>();
    private List<String> jobDescription = new ArrayList<>();
    private List<String> currentJob = new ArrayList<>();
    private List<Integer> skillId = new ArrayList<>();
    private List<Integer> skillLevel = new ArrayList<>();
    private List<Integer> languageId = new ArrayList<>();
    private List<Integer> languageLevel = new ArrayList<>();
    private String additionalInfo;

    public MultipartFile getPhoto() { return photo; }
    public void setPhoto(MultipartFile photo) { this.photo = photo; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    
    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    
    public Boolean getDriverLicense() { return driverLicense; }
    public void setDriverLicense(Boolean driverLicense) { this.driverLicense = driverLicense; }
    
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
    
    public Integer getEducationLevel() { return educationLevel; }
    public void setEducationLevel(Integer educationLevel) { this.educationLevel = educationLevel; }
    
    public Integer getLastDegree() { return lastDegree; }
    public void setLastDegree(Integer lastDegree) { this.lastDegree = lastDegree; }
    
    public Double getSalaryMin() { return salaryMin; }
    public void setSalaryMin(Double salaryMin) { this.salaryMin = salaryMin; }
    
    public Double getSalaryMax() { return salaryMax; }
    public void setSalaryMax(Double salaryMax) { this.salaryMax = salaryMax; }
    
    public List<String> getCompanyName() { return companyName; }
    public void setCompanyName(List<String> companyName) { this.companyName = companyName; }
    
    public List<String> getPosition() { return position; }
    public void setPosition(List<String> position) { this.position = position; }
    
    public List<String> getStartDate() { return startDate; }
    public void setStartDate(List<String> startDate) { this.startDate = startDate; }
    
    public List<String> getEndDate() { return endDate; }
    public void setEndDate(List<String> endDate) { this.endDate = endDate; }
    
    public List<String> getJobDescription() { return jobDescription; }
    public void setJobDescription(List<String> jobDescription) { this.jobDescription = jobDescription; }
    
    public List<String> getCurrentJob() { return currentJob; }
    public void setCurrentJob(List<String> currentJob) { this.currentJob = currentJob; }
    
    public List<Integer> getSkillId() { return skillId; }
    public void setSkillId(List<Integer> skillId) { this.skillId = skillId; }
    
    public List<Integer> getSkillLevel() { return skillLevel; }
    public void setSkillLevel(List<Integer> skillLevel) { this.skillLevel = skillLevel; }
    
    public List<Integer> getLanguageId() { return languageId; }
    public void setLanguageId(List<Integer> languageId) { this.languageId = languageId; }
    
    public List<Integer> getLanguageLevel() { return languageLevel; }
    public void setLanguageLevel(List<Integer> languageLevel) { this.languageLevel = languageLevel; }
    
    public String getAdditionalInfo() { return additionalInfo; }
    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }
    
    
}
