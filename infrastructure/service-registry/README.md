# Service Registry (Eureka Server)

Service discovery and registration server for Escala ERP microservices architecture.

## Overview

The Service Registry is the central component that enables microservices to discover and communicate with each other dynamically. It uses Netflix Eureka Server for service registration and discovery.

## Key Features

- ✅ **Service Registration** - Microservices register themselves automatically
- ✅ **Service Discovery** - Services can find each other by name
- ✅ **Health Monitoring** - Continuous health checks on registered services
- ✅ **Load Balancing** - Distributes requests across multiple instances
- ✅ **Automatic Failover** - Removes unhealthy instances automatically

## Port

- **Default**: `8761`

## Endpoints

### Eureka Dashboard
```
http://localhost:8761
```
Visual interface showing all registered services and their status.

### Health Check
```
http://localhost:8761/actuator/health
```

### Metrics
```
http://localhost:8761/actuator/metrics
```

## Configuration

### Main Configuration (`application.yml`)

```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false  # Don't register itself
    fetch-registry: false        # Don't fetch registry from others
```

## Running the Service

### Using Gradle

```bash
# From root project
./gradlew :infrastructure:service-registry:bootRun

# From service directory
cd infrastructure/service-registry
../../gradlew bootRun
```

### Using Java

```bash
# Build the JAR
./gradlew :infrastructure:service-registry:build

# Run the JAR
java -jar infrastructure/service-registry/build/libs/service-registry-1.0.0.jar
```

## Verification

Once started, verify the service is running:

1. **Check Dashboard**: Open `http://localhost:8761` in browser
2. **Check Health**: `curl http://localhost:8761/actuator/health`
3. **Expected Response**:
   ```json
   {
     "status": "UP"
   }
   ```

## How Other Services Register

Other microservices register by adding these dependencies and configuration:

### Dependency (`build.gradle`)
```groovy
implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
```

### Configuration (`application.yml`)
```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### Enable Discovery
```java
@SpringBootApplication
@EnableDiscoveryClient  // Add this annotation
public class MyServiceApplication {
    // ...
}
```

## Dashboard Information

The Eureka Dashboard shows:

- **System Status** - Server uptime, environment
- **DS Replicas** - Registered replica instances
- **Instances Currently Registered** - All active services
- **General Info** - Memory, CPU usage
- **Instance Info** - Detailed information per service

## Development vs Production

### Development Settings (Current)
```yaml
eureka:
  server:
    enable-self-preservation: false  # Faster detection of service failures
    eviction-interval-timer-in-ms: 10000  # Check every 10 seconds
```

### Production Settings (Recommended)
```yaml
eureka:
  server:
    enable-self-preservation: true  # Protect against network partitions
    eviction-interval-timer-in-ms: 60000  # Check every 60 seconds
```

## Troubleshooting

### Service Not Appearing in Dashboard

1. **Check service configuration**: Ensure `eureka.client.service-url.defaultZone` is correct
2. **Check network**: Verify service can reach port 8761
3. **Check logs**: Look for registration errors in service logs
4. **Wait**: Services take 30 seconds to appear after startup

### Service Showing as DOWN

1. **Check health endpoint**: Verify service health check is responding
2. **Check heartbeat**: Services send heartbeat every 30 seconds
3. **Check timeouts**: Network issues can cause timeouts
4. **Restart service**: Sometimes a restart resolves transient issues

## Dependencies

```groovy
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

## Testing

```bash
# Run tests
./gradlew :infrastructure:service-registry:test

# Run with coverage
./gradlew :infrastructure:service-registry:test jacocoTestReport
```

## Monitoring

Monitor the service using Spring Boot Actuator endpoints:

- `/actuator/health` - Health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Available metrics
- `/actuator/metrics/{metric}` - Specific metric details

## Architecture

```
┌─────────────────────────────────────┐
│     CLIENT (Frontend/Postman)       │
└───────────────┬─────────────────────┘
                │
                ▼
┌───────────────────────────────────────┐
│         SERVICE REGISTRY              │
│         (Eureka Server)               │
│         Port: 8761                    │
└───────────────┬───────────────────────┘
                │
    ┌───────────┼───────────┐
    │           │           │
    ▼           ▼           ▼
┌────────┐  ┌────────┐  ┌────────┐
│Service │  │Service │  │Service │
│   A    │  │   B    │  │   C    │
└────────┘  └────────┘  └────────┘
```

## Next Steps

After Service Registry is running:

1. Start **Config Server** (Port 8888)
2. Start **API Gateway** (Port 8080)
3. Start business services (they will auto-register here)

## Support

For issues or questions, contact the Escala ERP Team.