package com.example.gestionEntreprise.repository;
import org.springframework.stereotype.Repository;
import com.example.gestionEntreprise.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

} 
