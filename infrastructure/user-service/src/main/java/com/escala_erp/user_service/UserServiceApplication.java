package com.escala_erp.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User Service Application
 *
 * User management service for the Escala ERP microservices architecture.
 * Handles CRUD operations for users, roles, permissions, and user profiles.
 *
 * Key Responsibilities:
 * - User CRUD operations (create, read, update, soft delete)
 * - User profile management (personal information, preferences)
 * - Role and permission management
 * - User search and filtering
 * - User activation/deactivation
 * - Password change (delegates actual hashing to auth-service)
 *
 * Note: This service does NOT handle authentication or JWT generation.
 * Authentication is handled by auth-service. User-service only manages
 * user data and verifies permissions.
 *
 * Database Schema: users
 * - Uses PostgreSQL schema 'users'
 * - Schema managed by JPA/Hibernate
 *
 * Port: 8082
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-01-15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {

	/**
	 * Main method to start the User Service
	 *
	 * Prerequisites:
	 * - Service Registry (Eureka) running on port 8761
	 * - Config Server running on port 8888 (optional)
	 * - PostgreSQL running on port 5432 with database 'escala_erp'
	 * - Redis running on port 6379
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}