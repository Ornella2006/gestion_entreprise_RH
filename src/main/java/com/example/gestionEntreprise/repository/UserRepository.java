package com.example.gestionEntreprise.repository;

import org.springframework.stereotype.Repository;
import com.example.gestionEntreprise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsernameAndPassword(String username, String password);
    
}