package com.example.gestionEntreprise.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Employee {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployee;

    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_probation_date")
    private Date startProbationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_probation_date")
    private Date endProbationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "probation_status")
    private StatusType probationStatus;

    public Employee() {}


    public Integer getIdEmployee() { return idEmployee; }
    public void setIdEmployee(Integer idEmployee) { this.idEmployee = idEmployee; }
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public Date getStartProbationDate() { return startProbationDate; }
    public void setStartProbationDate(Date startProbationDate) { this.startProbationDate = startProbationDate; }
    public Date getEndProbationDate() { return endProbationDate; }
    public void setEndProbationDate(Date endProbationDate) { this.endProbationDate = endProbationDate; }
    public StatusType getProbationStatus() { return probationStatus; }
    public void setProbationStatus(StatusType probationStatus) { this.probationStatus = probationStatus; }

}
