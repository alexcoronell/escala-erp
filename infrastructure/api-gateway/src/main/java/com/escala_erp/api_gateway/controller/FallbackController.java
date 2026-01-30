package com.escala_erp.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Fallback Controller
 * -
 * Provides fallback responses when circuit breakers are triggered due to
 * service failures or timeouts. This prevents cascading failures and provides
 * graceful degradation of the system.
 * -
 * Each service has its own fallback endpoint that returns a user-friendly
 * error message when the service is temporarily unavailable.
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2024-01-28
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/auth")
    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> authServiceFallback() {
        return buildFallbackResponse(
                "Auth Service",
                "Authentication service is temporarily unavailable. Please try again later."
        );
    }

    /**
     * Fallback for User Service
     *
     * @return ResponseEntity with service unavailable message
     */
    @GetMapping("/users")
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> userServiceFallback() {
        return buildFallbackResponse(
                "User Service",
                "User service is temporarily unavailable. Please try again later."
        );
    }

    /**
     * Fallback for CRM Service
     *
     * @return ResponseEntity with service unavailable message
     */
    @GetMapping("/crm")
    @PostMapping("/crm")
    public ResponseEntity<Map<String, Object>> crmServiceFallback() {
        return buildFallbackResponse(
                "CRM Service",
                "CRM service is temporarily unavailable. Please try again later."
        );
    }

    /**
     * Build a standardized fallback response
     *
     * @param serviceName the name of the service that is unavailable
     * @param message the error message to display
     * @return ResponseEntity with error details
     */
    private ResponseEntity<Map<String, Object>> buildFallbackResponse(String serviceName, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
        response.put("error", "Service Unavailable");
        response.put("service", serviceName);
        response.put("message", message);
        response.put("suggestion", "Please check service health or try again in a few moments.");

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }
}
