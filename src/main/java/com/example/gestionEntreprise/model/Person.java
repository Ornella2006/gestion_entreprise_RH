package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;

    @ManyToOne
    @JoinColumn(name = "idMarital_Status")
    private MaritalStatus maritalStatus;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(length = 50)
    private String phone;

    @Column(length = 100)
    private String city;

    @Column(length = 255)
    private String linkedin;

    @Column(length = 10)
    private String gender;

    @Column(length = 100, unique = true)
    private String email;

    private Boolean driverLicense = false;

    // Getters and setters
    public Long getIdPerson() { return idPerson; }
    public void setIdPerson(Long idPerson) { this.idPerson = idPerson; }
    public MaritalStatus getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(MaritalStatus maritalStatus) { this.maritalStatus = maritalStatus; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getDriverLicense() { return driverLicense; }
    public void setDriverLicense(Boolean driverLicense) { this.driverLicense = driverLicense; }
}