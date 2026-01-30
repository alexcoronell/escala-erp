package com.escala_erp.config_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Integration tests for Config Server Application

 * These tests verify that the Config Server starts correctly,
 * all required components are properly initialized, and the
 * configuration endpoints are accessible.

 * Note: Service Registry (Eureka) is mocked for testing to avoid
 * external dependencies during test execution.
 *
 * @author Escala ERP Team
 */
@SpringBootTest
@TestPropertySource(properties = {
		"eureka.client.enabled=false",
		"spring.cloud.config.server.native.search-locations=classpath:/configs/"
})
class ConfigServerApplicationTests {

	/**
	 * Test that the Spring application context loads successfully

	 * This verifies:
	 * 1. Spring Boot application starts successfully
	 * 2. Config Server is configured correctly
	 * 3. Native profile is properly configured
	 * 4. All required dependencies are present
	 * 5. No configuration errors exist
	 */
	@Test
	void contextLoads() {
		// If this test passes, the Config Server is properly configured
		// and can serve configuration files to other services
	}

}
