package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.User;
import com.example.gestionEntreprise.model.Employee;
import com.example.gestionEntreprise.repository.UserRepository;
import com.example.gestionEntreprise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

public User findLoginUser(String username, String password) {
    User user = userRepository.findByUsernameAndPassword(username, password);
    System.out.println("User found: " + (user != null ? user.getIdUser() : "null"));
    System.out.println("Employee ID: " + (user != null && user.getEmployee() != null ? user.getEmployee().getIdEmployee() : "null"));
    return user;
}
    public Integer getIdEmployeeByIdUser(Integer idUser) {
        return userRepository.findIdEmployeeByIdUser(idUser);
    }
    public String getRoleNameByUserId(Integer idUser) {
        return userRepository.findRoleNameByUserId(idUser);
    }
    
}
