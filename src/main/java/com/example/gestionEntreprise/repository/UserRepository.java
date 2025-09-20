package com.example.gestionEntreprise.repository;

import com.example.gestionEntreprise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
   @Query("SELECT u FROM User u LEFT JOIN FETCH u.employee e LEFT JOIN FETCH e.role WHERE u.username = :username AND u.password = :password")
User findByUsernameAndPassword(@Param("username") String username, 
                              @Param("password") String password);
    
    // ✅ REQUÊTE EXPLICITE pour l'ID employee
    Integer findIdEmployeeByIdUser(Integer idUser);
    
    @Query("SELECT u.employee.role.roleName FROM User u WHERE u.idUser = :idUser")
    String findRoleNameByUserId(@Param("idUser") Integer idUser);
}