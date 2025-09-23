package com.example.gestionEntreprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.gestionEntreprise.model")
@EnableJpaRepositories("com.example.gestionEntreprise.repository")
public class GestionEntrepriseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEntrepriseApplication.class, args);
	}

}
