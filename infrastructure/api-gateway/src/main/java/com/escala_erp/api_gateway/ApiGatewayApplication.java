package com.escala_erp.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API Gateway Application
 *
 * Single entry point for all microservices in the Escala ERP architecture.
 * This gateway provides routing, load balancing, security, rate limiting,
 * and resilience patterns (circuit breaker, retry) for all backend services.
 *
 * Key Features:
 * - Single entry point for all microservices
 * - Dynamic routing based on service registry
 * - Load balancing across multiple service instances
 * - Circuit breaker for fault tolerance
 * - Rate limiting to prevent API abuse
 * - CORS configuration for web clients
 * - Request/response logging and monitoring
 *
 * Routing Examples:
 * - /api/auth/** → auth-service
 * - /api/users/** → user-service
 * - /api/crm/** → crm-service
 *
 * The gateway uses service discovery (Eureka) to dynamically route requests
 * to available service instances without hardcoded URLs.
 *
 * Access the Gateway at: http://localhost:8080
 *
 * Actuator Endpoints:
 * - /actuator/health - Health check
 * - /actuator/gateway/routes - View all configured routes
 * - /actuator/metrics - Application metrics
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-01-15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	/**
	 * Main method to start the API Gateway

	 * Prerequisites:
	 * - Service Registry (Eureka) must be running on port 8761
	 * - Config Server should be running on port 8888 (optional)
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
