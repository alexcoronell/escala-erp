package com.escala_erp.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config Server Application

 * Centralized configuration management server for the Escala ERP microservices architecture.
 * This server provides externalized configuration for all services, enabling configuration
 * changes without recompilation or redeployment.

 * Key Features:
 * - Centralized configuration management
 * - Support for multiple environments (dev, staging, prod)
 * - Configuration versioning via Git (production)
 * - Dynamic configuration refresh without service restart
 * - Encryption/decryption of sensitive properties

 * Configuration Sources (Priority Order):
 * 1. Service-specific config: {service-name}.yml
 * 2. Profile-specific config: {service-name}-{profile}.yml
 * 3. Shared config: application.yml
 * 4. Profile-specific shared: application-{profile}.yml

 * Access Configuration:
 * - http://localhost:8888/{service-name}/default
 * - http://localhost:8888/{service-name}/{profile}
 *
 * Examples:
 * - http://localhost:8888/auth-service/default
 * - http://localhost:8888/user-service/dev
 * - http://localhost:8888/crm-service/prod
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2024-01-28
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	/**
	 * Main method to start the Config Server

	 * Prerequisites:
	 * - Service Registry (Eureka) must be running on port 8761
	 * - Configuration files must be present in src/main/resources/configs/

	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
