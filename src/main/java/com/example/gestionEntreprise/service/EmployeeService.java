package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Employee;
import com.example.gestionEntreprise.model.User;
import com.example.gestionEntreprise.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

}
