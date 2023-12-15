package com.upc.backend.akira;

import com.upc.backend.akira.ecommerce.domain.model.enums.ERole;
import com.upc.backend.akira.ecommerce.domain.repository.RoleRepository;
import com.upc.backend.akira.shared.util.Utilities;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Akira Ecommerce API", version = "1.0.0", description = "El API de nuestro E-commerce en Perú"))
public class AkiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkiraApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepository) {
		return args -> {
			Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_USER);
			Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_ADMIN);
		};
	}
}
