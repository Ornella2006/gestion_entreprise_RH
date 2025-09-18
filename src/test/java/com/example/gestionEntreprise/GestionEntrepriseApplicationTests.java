package com.example.gestionEntreprise;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.gestionEntreprise.model.User;
import com.example.gestionEntreprise.repository.UserRepository;

@SpringBootTest
class GestionEntrepriseApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource; // Injecte la source de données

    @Test
    public void testDatabaseConnection() throws SQLException {
        System.out.println("Testing database connection...");
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Database connection should not be null");
            assertTrue(connection.isValid(2), "Database connection should be valid");
            System.out.println("Database connection successful: " + connection.getMetaData().getURL());
        }
    }

    @Test
    public void testFindByUsernameAndPassword() {
        System.out.println("Testing findByUsernameAndPassword for username=alice_admin, password=admin123");
        System.out.println("All users in database: " + userRepository.findAll());
        User user = userRepository.findByUsernameAndPassword("alice_admin", "admin123");
        System.out.println("User found: " + user);
        assertNotNull(user, "User should not be null for username=alice_admin and password=admin123");
    }

    @Test
    void contextLoads() {
    }
}