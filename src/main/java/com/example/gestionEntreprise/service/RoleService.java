package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Role;

import com.example.gestionEntreprise.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role findRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
    
}
