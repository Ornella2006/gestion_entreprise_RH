package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idperson")
    private Integer idPerson;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "linkedin", length = 255)
    private String linkedin;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "driver_license")
    private Boolean driverLicense = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatusType maritalStatus;

    // Constructeurs
    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate birthDate, 
                 String phone, String city, String linkedin, String gender, 
                 String email, Boolean driverLicense, MaritalStatusType maritalStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.city = city;
        this.linkedin = linkedin;
        this.gender = gender;
        this.email = email;
        this.driverLicense = driverLicense;
        this.maritalStatus = maritalStatus;
    }

    // Getters et Setters
    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(Boolean driverLicense) {
        this.driverLicense = driverLicense;
    }

    public MaritalStatusType getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatusType maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", driverLicense=" + driverLicense +
                ", maritalStatus=" + maritalStatus +
                '}';
    }
}