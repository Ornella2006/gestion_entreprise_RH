package com.example.gestionEntreprise.repository;

import org.springframework.stereotype.Repository;
import com.example.gestionEntreprise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
