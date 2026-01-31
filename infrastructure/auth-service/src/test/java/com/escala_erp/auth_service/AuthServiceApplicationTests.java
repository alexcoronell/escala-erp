package com.escala_erp.auth_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Integration tests for Auth Service Application
 *
 * Basic smoke test to verify that the Spring application context
 * loads successfully with all required configurations.
 *
 * External dependencies are mocked to avoid requiring real infrastructure.
 *
 * @author Alex Coronell
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
		// Disable Eureka for tests
		"eureka.client.enabled=false",

		// Use H2 in-memory database for tests
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"spring.jpa.hibernate.ddl-auto=create-drop",

		// Disable Flyway for tests
		"spring.flyway.enabled=false",

		// Mock Redis for tests
		"spring.data.redis.host=localhost",
		"spring.data.redis.port=6379"
})
class AuthServiceApplicationTests {

	/**
	 * Smoke test: verify that the Spring application context loads successfully
	 *
	 * This test validates:
	 * 1. Spring Boot application starts without errors
	 * 2. All required beans are created
	 * 3. Configuration is valid
	 * 4. No circular dependencies exist
	 */
	@Test
	void contextLoads() {
		// If this test passes, the basic infrastructure is correctly configured
	}
}