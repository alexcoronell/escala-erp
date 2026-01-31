package com.escala_erp.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Auth Service Application
 *
 * Authentication and authorization service for the Escala ERP microservices architecture.
 * Handles user authentication, JWT token generation/validation, OAuth2 integration,
 * and session management.
 *
 * Key Responsibilities:
 * - User authentication (login/logout)
 * - JWT token generation and validation
 * - Refresh token management
 * - OAuth2 integration (Google, Facebook, etc.)
 * - Session management with Redis
 * - Password encryption and validation
 *
 * Security Stack:
 * - Spring Security 7.0.2
 * - JWT for stateless authentication
 * - BCrypt for password hashing
 * - Redis for token blacklisting
 *
 * Database Schema: auth
 * - Uses PostgreSQL schema 'auth'
 * - Schema managed by JPA/Hibernate
 *
 * Port: 8081
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-01-15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthServiceApplication {

	/**
	 * Main method to start the Auth Service
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
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}