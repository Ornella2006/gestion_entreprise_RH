package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"") // Échappement des guillemets pour le nom de table réservé
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "idemployee", referencedColumnName = "idemployee")
    private Employee employee;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // Constructeurs
    public User() {
    }

    public User(Employee employee, String username, String password) {
        this.employee = employee;
        this.username = username;
        this.password = password;
    }

    // Getters et Setters
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", employee=" + employee +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}