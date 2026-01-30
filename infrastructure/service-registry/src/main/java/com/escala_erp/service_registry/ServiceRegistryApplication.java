package com.escala_erp.service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Service Registry Application (Eureka Server)

 * This is the central service discovery server for the Escala ERP microservices architecture.
 * All microservices register themselves here, enabling dynamic service-to-service communication
 * without hardcoded URLs.

 * Key Features:
 * - Service registration and discovery
 * - Health monitoring of registered services
 * - Load balancing support
 * - Automatic failover

 * Access the Eureka Dashboard at: http://localhost:8761

 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-01-15
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	/**
	* Main method to start the Service Registry (Eureka Server)*
	* @param args command line arguments
    */
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
