package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.User;
import com.example.gestionEntreprise.repository.UserRepository;
import com.example.gestionEntreprise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;


    public User findLoginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    
}
