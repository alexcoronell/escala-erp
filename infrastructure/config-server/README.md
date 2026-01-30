# Config Server

Centralized configuration management server for Escala ERP microservices.

## Overview

Config Server provides externalized configuration for all microservices in the Escala ERP architecture. It enables configuration changes without recompilation or redeployment, supporting multiple environments and profiles.

## Key Features

- ✅ **Centralized Configuration** - All service configs in one place
- ✅ **Environment Profiles** - dev, staging, prod configurations
- ✅ **Dynamic Refresh** - Update configs without restart (via `/actuator/refresh`)
- ✅ **Version Control** - Git-based configuration (production)
- ✅ **Encryption Support** - Secure sensitive properties
- ✅ **Service Discovery** - Registers with Eureka

## Port

- **Default**: `8888`

## Endpoints

### Get Configuration for a Service
```
GET http://localhost:8888/{service-name}/{profile}
```

**Examples:**
```bash
# Default profile
curl http://localhost:8888/auth-service/default

# Development profile
curl http://localhost:8888/user-service/dev

# Production profile
curl http://localhost:8888/crm-service/prod
```

### Health Check
```
GET http://localhost:8888/actuator/health
```

### Refresh Configuration
```
POST http://localhost:8888/actuator/refresh
```

## Configuration Files Location

### Development (Native Profile)
```
src/main/resources/configs/
├── application.yml              # Shared config for all services
├── application-dev.yml          # Development environment
├── application-prod.yml         # Production environment
├── auth-service.yml             # Auth service specific
├── user-service.yml             # User service specific
├── crm-service.yml              # CRM service specific
└── ...                          # Other service configs
```

### Production (Git Profile)
```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/config-repo.git
          default-label: main
          search-paths: configs
```

## Configuration Priority

Configurations are applied in this order (highest to lowest priority):

1. **Service-specific profile config**: `{service-name}-{profile}.yml`
2. **Service-specific config**: `{service-name}.yml`
3. **Shared profile config**: `application-{profile}.yml`
4. **Shared config**: `application.yml`

**Example for auth-service in dev profile:**
```
1. auth-service-dev.yml    (highest priority)
2. auth-service.yml
3. application-dev.yml
4. application.yml          (lowest priority)
```

## Running the Service

### Prerequisites

**Service Registry must be running:**
```bash
# Start Eureka Server first
./gradlew :infrastructure:service-registry:bootRun
```

### Start Config Server

**Using Gradle:**
```bash
# From root project
./gradlew :infrastructure:config-server:bootRun

# From service directory
cd infrastructure/config-server
../../gradlew bootRun
```

**Using Java:**
```bash
# Build the JAR
./gradlew :infrastructure:config-server:build

# Run the JAR
java -jar infrastructure/config-server/build/libs/config-server-1.0.0.jar
```

## Verification

### 1. Check Service Registration
Open Eureka Dashboard: http://localhost:8761
- Should see `CONFIG-SERVER` in the list of registered services

### 2. Check Health
```bash
curl http://localhost:8888/actuator/health
```

**Expected Response:**
```json
{
  "status": "UP"
}
```

### 3. Test Configuration Retrieval
```bash
curl http://localhost:8888/auth-service/default
```

**Expected Response:**
```json
{
  "name": "auth-service",
  "profiles": ["default"],
  "label": null,
  "version": null,
  "state": null,
  "propertySources": [...]
}
```

## How Services Use Config Server

### Step 1: Add Dependency
```groovy
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-config'
}
```

### Step 2: Create bootstrap.yml
```yaml
spring:
  application:
    name: auth-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: true
```

### Step 3: Start Service
The service will automatically fetch its configuration from Config Server on startup.

## Dynamic Configuration Refresh

### Enable Refresh in Service
```java
@RestController
@RefreshScope  // Add this annotation
public class MyController {
    @Value("${my.property}")
    private String myProperty;
}
```

### Trigger Refresh
```bash
# After changing config in Config Server
curl -X POST http://localhost:8081/actuator/refresh
```

## Configuration Examples

### Shared Configuration (application.yml)
```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

### Service-Specific (auth-service.yml)
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/escala_erp
    schema: auth
```

### Environment-Specific (application-dev.yml)
```yaml
logging:
  level:
    root: DEBUG

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/escala_erp_dev
```

## Encryption/Decryption

### Encrypt a Value
```bash
curl http://localhost:8888/encrypt -d "my-secret-password"
```

### Use Encrypted Value in Config
```yaml
spring:
  datasource:
    password: '{cipher}AQA9JnLh4...encrypted-value'
```

### Decrypt (automatic)
Services automatically decrypt values prefixed with `{cipher}`.

## Troubleshooting

### Service Can't Connect to Config Server

**Check:**
1. Config Server is running on port 8888
2. Service has correct `spring.cloud.config.uri`
3. Network connectivity between services

**Solution:**
```yaml
# In service's bootstrap.yml
spring:
  cloud:
    config:
      fail-fast: false  # Don't fail if Config Server is down
      retry:
        max-attempts: 6
```

### Configuration Not Loading

**Check:**
1. File name matches service name
2. File is in correct location (`configs/` directory)
3. YAML syntax is valid

**Debug:**
```bash
# Check what Config Server is serving
curl http://localhost:8888/{service-name}/default
```

### Refresh Not Working

**Ensure:**
1. `@RefreshScope` annotation on beans
2. Actuator refresh endpoint is enabled
3. POST request to `/actuator/refresh`

## Development vs Production

### Development (Current)
```yaml
spring:
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/configs/
```

### Production (Recommended)
```yaml
spring:
  profiles:
    active: git
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/config-repo.git
          username: ${GIT_USERNAME}
          password: ${GIT_PASSWORD}
          default-label: main
```

## Dependencies

```groovy
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-config-server'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

## Testing

```bash
# Run tests
./gradlew :infrastructure:config-server:test

# Run with coverage
./gradlew :infrastructure:config-server:test jacocoTestReport
```

## Monitoring

Monitor using Spring Boot Actuator endpoints:

- `/actuator/health` - Health status
- `/actuator/info` - Application information
- `/actuator/env` - Environment properties
- `/actuator/configprops` - Configuration properties

## Architecture

```
┌──────────────────────────────────┐
│      CONFIG-SERVER               │
│      Port: 8888                  │
│                                  │
│  ┌────────────────────────────┐  │
│  │  Configs Repository        │  │
│  │  (Native/Git)              │  │
│  │                            │  │
│  │  - application.yml         │  │
│  │  - auth-service.yml        │  │
│  │  - user-service.yml        │  │
│  │  - crm-service.yml         │  │
│  └────────────────────────────┘  │
└──────────────┬───────────────────┘
               │
    ┌──────────┼──────────┐
    │          │          │
    ▼          ▼          ▼
┌────────┐ ┌────────┐ ┌────────┐
│ Auth   │ │ User   │ │  CRM   │
│Service │ │Service │ │Service │
└────────┘ └────────┘ └────────┘
```

## Next Steps

After Config Server is running:

1. Start **API Gateway** (Port 8080)
2. Configure other services to use Config Server
3. Create service-specific configuration files

## Support

For issues or questions, contact the Alex Coronell.