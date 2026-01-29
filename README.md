# Escala ERP - Enterprise Resource Planning System

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=flat-square&logo=spring)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.0-brightgreen?style=flat-square&logo=spring)
![Gradle](https://img.shields.io/badge/Gradle-8.x-blue?style=flat-square&logo=gradle)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

A complete enterprise ERP system with a microservices architecture that integrates CRM, sales, marketing, accounting, inventory, purchasing, human resources, e-commerce, and real-time chat. It implements Clean Architecture, multi-tenancy, JWT-based security, and DDD patterns for scalable, end-to-end enterprise process management.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Microservices](#microservices)
- [Database Architecture](#database-architecture)
- [Project Structure](#project-structure)
- [Communication Patterns](#communication-patterns)
- [Security](#security)
- [Getting Started](#getting-started)
- [Development Phases](#development-phases)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

Escala ERP is a modern, cloud-native enterprise resource planning system built with microservices architecture. It provides comprehensive business management capabilities including:

- **Customer Relationship Management (CRM)**
- **Sales & Order Management**
- **Marketing Automation**
- **Financial Accounting**
- **Inventory Management**
- **Purchase & Procurement**
- **Human Resources Management**
- **E-commerce Integration**
- **Real-time Chat & Collaboration**

### Key Features

- ✅ **Microservices Architecture** - Scalable and maintainable
- ✅ **Multi-tenancy Support** - Single instance, multiple organizations
- ✅ **Clean Architecture** - Domain-driven design (DDD)
- ✅ **Event-Driven** - Asynchronous communication via RabbitMQ
- ✅ **API Gateway** - Single entry point with routing and security
- ✅ **Service Discovery** - Automatic service registration with Eureka
- ✅ **Centralized Configuration** - Spring Cloud Config Server
- ✅ **JWT Authentication** - Secure token-based authentication
- ✅ **Real-time Communication** - WebSocket support for chat
- ✅ **Comprehensive Testing** - Unit, integration, and E2E tests

---

## 🏗️ Architecture

### High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                         INTERNET                            │
└───────────────────────────┬─────────────────────────────────┘
                            │
                ┌───────────▼───────────┐
                │    LOAD BALANCER      │
                │    (NGINX/HAProxy)    │
                └───────────┬───────────┘
                            │
                ┌───────────▼───────────┐
                │     API GATEWAY       │
                │  Spring Cloud Gateway │
                │      Port: 8080       │
                └───────────┬───────────┘
                            │
        ┌───────────────────┼───────────────────┐
        │                   │                   │
┌───────▼────────┐  ┌───────▼────────┐  ┌──────▼─────────┐
│ SERVICE        │  │ CONFIG         │  │ INFRASTRUCTURE │
│ REGISTRY       │  │ SERVER         │  │ SERVICES       │
│ (Eureka)       │  │                │  │                │
│ Port: 8761     │  │ Port: 8888     │  │ - Auth: 8081   │
└────────────────┘  └────────────────┘  │ - User: 8082   │
                                        └────────────────┘
                            │
        ┌───────────────────┼───────────────────┐
        │                   │                   │
┌───────▼────────┐  ┌───────▼────────┐  ┌──────▼─────────┐
│ BUSINESS       │  │ BUSINESS       │  │ SUPPORT        │
│ SERVICES       │  │ SERVICES       │  │ SERVICES       │
│                │  │                │  │                │
│ - CRM: 8083    │  │ - Inv: 8087    │  │ - Notif: 8092  │
│ - Sales: 8084  │  │ - Purch: 8088  │  │ - File: 8093   │
│ - Mkt: 8085    │  │ - HR: 8089     │  │ - Report: 8094 │
│ - Acct: 8086   │  │ - Ecom: 8090   │  │                │
│                │  │ - Chat: 8091   │  │                │
└────────────────┘  └────────────────┘  └────────────────┘
```

### Architectural Principles

1. **Clean Architecture** - Separation of concerns with clear boundaries
2. **Domain-Driven Design (DDD)** - Business logic at the core
3. **SOLID Principles** - Maintainable and extensible code
4. **Microservices Pattern** - Independent, deployable services
5. **API Gateway Pattern** - Centralized entry point
6. **Service Registry Pattern** - Dynamic service discovery
7. **Event-Driven Architecture** - Asynchronous communication
8. **CQRS (Optional)** - Command Query Responsibility Segregation

---

## 💻 Technology Stack

### Core Technologies

| Technology        | Version     | Purpose                 |
|-------------------|-------------|-------------------------|
| **Java**          | 21          | Programming language    |
| **Spring Boot**   | 4.0.2       | Application framework   |
| **Spring Cloud**  | 2025.1.0    | Microservices framework |
| **Gradle**        | 8.x         | Build tool              |
| **Lombok**        | 1.18.42     | Boilerplate reduction   |
| **MapStruct**     | 1.5.5.Final | Object mapping          |

### Infrastructure

| Technology               | Purpose                        |
|--------------------------|--------------------------------|
| **Netflix Eureka**       | Service registry               |
| **Spring Cloud Gateway** | API Gateway                    |
| **Spring Cloud Config**  | Centralized configuration      |
| **Spring Security**      | Authentication & authorization |
| **JWT**                  | Token-based authentication     |

### Data Storage

| Technology        | Purpose                     |
|-------------------|-----------------------------|
| **PostgreSQL**    | Primary relational database |
| **MongoDB**       | Chat message history        |
| **Redis**         | Caching and sessions        |
| **Elasticsearch** | Search and analytics        |
| **MinIO/S3**      | Object storage for files    |

### Messaging & Communication

| Technology    | Purpose                                |
|---------------|----------------------------------------|
| **RabbitMQ**  | Message broker for async communication |
| **WebSocket** | Real-time bidirectional communication  |
| **OpenFeign** | Declarative REST client                |

### DevOps & Monitoring

| Technology         | Purpose                              |
|--------------------|--------------------------------------|
| **Docker**         | Containerization                     |
| **Docker Compose** | Local development environment        |
| **Kubernetes**     | Container orchestration (production) |
| **Flyway**         | Database migrations                  |
| **Prometheus**     | Metrics collection                   |
| **Grafana**        | Metrics visualization                |
| **Zipkin**         | Distributed tracing                  |
| **ELK Stack**      | Centralized logging                  |

---

## 🔧 Microservices

### Infrastructure Services (5)

Core infrastructure required for microservices architecture.

| Service              | Port | Description                           | Key Technologies          |
|----------------------|------|---------------------------------------|---------------------------|
| **service-registry** | 8761 | Service discovery and registration    | Netflix Eureka Server     |
| **config-server**    | 8888 | Centralized configuration management  | Spring Cloud Config       |
| **api-gateway**      | 8080 | API Gateway with routing and security | Spring Cloud Gateway, JWT |
| **auth-service**     | 8081 | Authentication, authorization, JWT    | Spring Security, OAuth2   |
| **user-service**     | 8082 | User management, roles, permissions   | Spring Data JPA           |

### Business Services (9)

Core business logic services.

| Service                 | Port | Description                        | Key Entities                                |
|-------------------------|------|------------------------------------|---------------------------------------------|
| **crm-service**         | 8083 | Customer relationship management   | Contacts, Leads, Activities, Opportunities  |
| **sales-service**       | 8084 | Sales and order management         | Customers, Quotations, Orders, Invoices     |
| **marketing-service**   | 8085 | Marketing campaigns and automation | Campaigns, Email Marketing, Forms           |
| **accounting-service**  | 8086 | Financial accounting               | Chart of Accounts, Journal Entries, Reports |
| **inventory-service**   | 8087 | Inventory and warehouse management | Products, Warehouses, Stock, Movements      |
| **purchase-service**    | 8088 | Purchase and procurement           | Suppliers, Purchase Orders, Receipts        |
| **hr-service**          | 8089 | Human resources management         | Employees, Payroll, Leave Management        |
| **ecommerce-service**   | 8090 | E-commerce storefront              | Online Store, Shopping Cart, Wishlist       |
| **chat-service** | 8091 | Real-time chat and collaboration | Chat Rooms, Messages, File Sharing |

### Support Services (3)

Cross-cutting support services.

| Service                  | Port | Description                     | Technologies                  |
|--------------------------|------|---------------------------------|-------------------------------|
| **notification-service** | 8092 | Email, SMS, push notifications  | JavaMail, WebSocket, Firebase |
| **file-service**         | 8093 | File upload and storage         | MinIO/S3, Multipart Upload    |
| **report-service**       | 8094 | Report generation and analytics | JasperReports, Apache POI     |

### Total: 17 Microservices

- **Infrastructure**: 5 services
- **Business**: 9 services
- **Support**: 3 services

---

## 🗄️ Database Architecture

### PostgreSQL - Single Database with Multiple Schemas

**Database Name**: `escala_erp`

We use a **single PostgreSQL database** with **separate schemas per service** to maintain:
- ✅ ACID transactions across services when needed
- ✅ Efficient cross-schema JOINs
- ✅ Foreign key constraints
- ✅ Simplified backups
- ✅ Better development experience

#### Schema Organization

```sql
-- Infrastructure Schemas
CREATE SCHEMA auth;           -- auth-service
CREATE SCHEMA users;          -- user-service

-- Business Schemas
CREATE SCHEMA crm;            -- crm-service
CREATE SCHEMA sales;          -- sales-service
CREATE SCHEMA marketing;      -- marketing-service
CREATE SCHEMA accounting;     -- accounting-service
CREATE SCHEMA inventory;      -- inventory-service
CREATE SCHEMA purchase;       -- purchase-service
CREATE SCHEMA hr;             -- hr-service
CREATE SCHEMA ecommerce;      -- ecommerce-service
CREATE SCHEMA chat;           -- chat-service (metadata)

-- Support Schemas
CREATE SCHEMA notification;   -- notification-service
CREATE SCHEMA files;          -- file-service
CREATE SCHEMA reports;        -- report-service
```

#### Multi-Tenancy Design

All tables include a `tenant_id` column for multi-tenancy support:

```sql
CREATE TABLE crm.contacts (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_tenant FOREIGN KEY (tenant_id) 
        REFERENCES users.companies(id)
);

CREATE INDEX idx_contacts_tenant ON crm.contacts(tenant_id);
```

### Specialized Databases

| Database         | Technology    | Purpose                                           |
|------------------|---------------|---------------------------------------------------|
| **chat_history** | MongoDB       | Chat message history and archives                 |
| **cache**        | Redis         | Sessions, cache, rate limiting, pub/sub           |
| **search**       | Elasticsearch | Full-text search for products, contacts, messages |

### Database Migration

- **Flyway** is used for version-controlled database migrations
- Each microservice manages its own schema migrations
- Migrations are located in `src/main/resources/db/migration/`

Example:
```
auth-service/src/main/resources/db/migration/
├── V001__init_auth_schema.sql
├── V002__add_oauth2_tables.sql
└── V003__add_refresh_tokens.sql
```

---

## 📁 Project Structure

### Root Project Structure

```
escala-erp/
├── infrastructure/              # Infrastructure services (5)
│   ├── service-registry/
│   ├── config-server/
│   ├── api-gateway/
│   ├── auth-service/
│   └── user-service/
│
├── business-services/           # Business services (9)
│   ├── crm-service/
│   ├── sales-service/
│   ├── marketing-service/
│   ├── accounting-service/
│   ├── inventory-service/
│   ├── purchase-service/
│   ├── hr-service/
│   ├── ecommerce-service/
│   └── chat-service/
│
├── support-services/            # Support services (3)
│   ├── notification-service/
│   ├── file-service/
│   └── report-service/
│
├── shared/                      # Shared libraries (created on demand)
│   ├── common-lib/              # Common DTOs, utilities, exceptions
│   └── security-lib/            # Security utilities, JWT helpers
│
├── docker/                      # Docker configurations
│   ├── docker-compose.yml
│   ├── docker-compose.prod.yml
│   └── nginx/
│
├── kubernetes/                  # Kubernetes manifests
│   ├── namespaces/
│   ├── deployments/
│   ├── services/
│   └── configmaps/
│
├── .gitignore
├── build.gradle                 # Root build configuration
├── settings.gradle              # Multi-module configuration
├── gradle.properties            # Gradle properties
├── gradlew                      # Gradle wrapper (Unix)
├── gradlew.bat                  # Gradle wrapper (Windows)
└── README.md
```

### Microservice Internal Structure (Clean Architecture)

Each microservice follows Clean Architecture principles:

```
service-name/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/escala/erp/{service}/
│   │   │       ├── ServiceApplication.java    # Main application class
│   │   │       │
│   │   │       ├── domain/                    # Domain layer (business logic)
│   │   │       │   ├── model/                 # Entities
│   │   │       │   ├── repository/            # Repository interfaces
│   │   │       │   └── service/               # Domain services
│   │   │       │
│   │   │       ├── application/               # Application layer (use cases)
│   │   │       │   ├── dto/                   # Data Transfer Objects
│   │   │       │   ├── usecase/               # Use case implementations
│   │   │       │   └── port/                  # Port interfaces
│   │   │       │
│   │   │       ├── infrastructure/            # Infrastructure layer
│   │   │       │   ├── persistence/           # JPA repositories, entities
│   │   │       │   ├── messaging/             # RabbitMQ, events
│   │   │       │   ├── external/              # External API clients
│   │   │       │   └── config/                # Configuration classes
│   │   │       │
│   │   │       └── presentation/              # Presentation layer
│   │   │           ├── rest/                  # REST controllers
│   │   │           └── exception/             # Exception handlers
│   │   │
│   │   └── resources/
│   │       ├── application.yml                # Application configuration
│   │       ├── application-dev.yml            # Development profile
│   │       ├── application-prod.yml           # Production profile
│   │       └── db/migration/                  # Flyway migrations
│   │           ├── V001__init_schema.sql
│   │           └── V002__add_indexes.sql
│   │
│   └── test/
│       ├── java/                              # Test classes
│       └── resources/                         # Test resources
│
└── build.gradle                               # Service-specific dependencies
```

---

## 🔄 Communication Patterns

### 1. Synchronous Communication (REST)

Services communicate synchronously via REST APIs using **OpenFeign**.

```java
@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/api/products/{id}")
    ProductDTO getProduct(@PathVariable Long id);
}
```

**Technologies:**
- OpenFeign (declarative REST client)
- Resilience4j (circuit breaker, retry, rate limiter)
- Spring Cloud LoadBalancer

### 2. Asynchronous Communication (Event-Driven)

Services communicate asynchronously via **RabbitMQ** for event-driven architecture.

```java
// Publisher
@Service
public class OrderService {
    public void createOrder(Order order) {
        // ... create order
        rabbitTemplate.convertAndSend("order.exchange", 
                                      "order.created", 
                                      new OrderCreatedEvent(order));
    }
}

// Consumer
@RabbitListener(queues = "inventory.queue")
public void handleOrderCreated(OrderCreatedEvent event) {
    // Reserve inventory
}
```

**Event Types:**
- Domain Events (OrderCreatedEvent, PaymentProcessedEvent)
- Integration Events (cross-service communication)
- Notification Events (email, SMS, push notifications)

### 3. Real-Time Communication (WebSocket)

The **chat-service** uses WebSocket for real-time bidirectional communication.

```javascript
// Client-side
const socket = new SockJS('/ws/chat');
const stompClient = Stomp.over(socket);

stompClient.subscribe('/topic/room/123', (message) => {
    console.log('New message:', message.body);
});
```

**Technologies:**
- WebSocket (STOMP protocol)
- SockJS (WebSocket fallback)
- Redis Pub/Sub (for multi-instance support)

---

## 🔐 Security

### Authentication Flow

```
┌─────────────────────────────────────────────────────────────┐
│                    CLIENT (Frontend)                        │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            │ 1. POST /api/auth/login
                            │    {username, password}
                            ▼
                ┌───────────────────────┐
                │    API GATEWAY        │
                │  (No JWT required)    │
                └───────────┬───────────┘
                            │
                            │ 2. Forward to auth-service
                            ▼
                ┌───────────────────────┐
                │   AUTH-SERVICE        │
                │  - Validate creds     │
                │  - Generate JWT       │
                │  - Return tokens      │
                └───────────┬───────────┘
                            │
                            │ 3. Response
                            │    {accessToken, refreshToken}
                            ▼
                ┌───────────────────────┐
                │       CLIENT          │
                │  Store tokens         │
                └───────────┬───────────┘
                            │
                            │ 4. Subsequent requests
                            │    Header: Authorization: Bearer {JWT}
                            ▼
                ┌───────────────────────┐
                │    API GATEWAY        │
                │  - Validate JWT       │
                │  - Extract claims     │
                │  - Add headers:       │
                │    X-User-Id          │
                │    X-Tenant-Id        │
                └───────────┬───────────┘
                            │
                            │ 5. Forward with context
                            ▼
                ┌───────────────────────┐
                │   BUSINESS SERVICES   │
                │  - Read headers       │
                │  - Apply multi-tenant │
                │  - Execute logic      │
                └───────────────────────┘
```

### Security Features

- **JWT-based Authentication** - Stateless, scalable authentication
- **OAuth2 Support** - Integration with third-party identity providers
- **Role-Based Access Control (RBAC)** - Fine-grained permissions
- **Multi-Tenancy** - Data isolation per organization
- **API Rate Limiting** - Prevent abuse
- **HTTPS/TLS** - Encrypted communication
- **CORS Configuration** - Controlled cross-origin requests
- **SQL Injection Prevention** - Parameterized queries
- **XSS Protection** - Input sanitization

### JWT Token Structure

```json
{
  "sub": "user123",
  "userId": 123,
  "tenantId": 1,
  "email": "user@example.com",
  "roles": ["ROLE_USER", "ROLE_SALES_MANAGER"],
  "permissions": ["READ_CUSTOMERS", "WRITE_ORDERS"],
  "iat": 1234567890,
  "exp": 1234571490
}
```

---

## 🚀 Getting Started

### Prerequisites

- **JDK 21** or higher
- **Gradle 8.x** (included via wrapper)
- **Docker & Docker Compose** (for local infrastructure)
- **PostgreSQL 16** (or use Docker)
- **Redis** (or use Docker)
- **RabbitMQ** (or use Docker)
- **Git**

### Quick Start (Local Development)

#### 1. Clone the repository

```bash
git clone https://github.com/alexcoronell/escala-erp.git
cd escala-erp
```

#### 2. Start infrastructure services with Docker

```bash
cd docker
docker-compose up -d
```

This will start:
- PostgreSQL (port 5432)
- Redis (port 6379)
- RabbitMQ (port 5672, management UI: 15672)
- MongoDB (port 27017)
- Elasticsearch (port 9200)
- MinIO (port 9000)

#### 3. Build all services

```bash
./gradlew clean build
```

#### 4. Start services in order

**Terminal 1 - Service Registry:**
```bash
./gradlew :infrastructure:service-registry:bootRun
```
Wait until it's running (check http://localhost:8761)

**Terminal 2 - Config Server:**
```bash
./gradlew :infrastructure:config-server:bootRun
```

**Terminal 3 - API Gateway:**
```bash
./gradlew :infrastructure:api-gateway:bootRun
```

**Terminal 4 - Auth Service:**
```bash
./gradlew :infrastructure:auth-service:bootRun
```

**Terminal 5 - User Service:**
```bash
./gradlew :infrastructure:user-service:bootRun
```

#### 5. Verify services are running

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)

#### 6. Test the API

```bash
# Register a new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "email": "admin@example.com",
    "password": "Password123!"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "Password123!"
  }'

# Use the returned JWT token for authenticated requests
curl -X GET http://localhost:8080/api/users/me \
  -H "Authorization: Bearer {your-jwt-token}"
```

### Development Profiles

Each service supports multiple profiles:

- **dev** - Development environment (default)
- **test** - Testing environment
- **prod** - Production environment

Set the profile using:
```bash
./gradlew :service-name:bootRun --args='--spring.profiles.active=dev'
```

Or via environment variable:
```bash
export SPRING_PROFILES_ACTIVE=dev
./gradlew :service-name:bootRun
```

---

## 📅 Development Phases

### Phase 1: Infrastructure Setup ✅ (Weeks 1-2)

- [x] Project structure and build configuration
- [ ] service-registry (Eureka Server)
- [ ] config-server (Configuration Server)
- [ ] api-gateway (API Gateway)
- [ ] auth-service (Authentication)
- [ ] user-service (User Management)

**Deliverable**: Functional authentication system

### Phase 2: First Business Service 🚧 (Weeks 3-4)

- [ ] crm-service (Contacts, Leads, Activities)
- [ ] Basic CRUD operations
- [ ] Integration with auth-service
- [ ] Basic reporting

**Deliverable**: Working CRM module

### Phase 3: Core Business Services (Month 2)

- [ ] sales-service (Orders, Invoices, Customers)
- [ ] inventory-service (Products, Stock, Warehouses)
- [ ] Integration between CRM and Sales
- [ ] Order-to-invoice workflow

**Deliverable**: Sales and inventory management

### Phase 4: Financial & Operations (Month 3)

- [ ] accounting-service (Chart of Accounts, Journal Entries)
- [ ] purchase-service (Suppliers, Purchase Orders)
- [ ] Integration with sales and inventory
- [ ] Financial reports

**Deliverable**: Complete financial management

### Phase 5: Human Resources & Marketing (Month 4)

- [ ] hr-service (Employees, Payroll, Leave Management)
- [ ] marketing-service (Campaigns, Email Marketing)
- [ ] Integration with CRM

**Deliverable**: HR and marketing modules

### Phase 6: E-commerce & Chat (Month 5)

- [ ] ecommerce-service (Online Store, Shopping Cart)
- [ ] chat-service (Real-time Chat, WebSocket)
- [ ] Integration with inventory and sales

**Deliverable**: E-commerce platform with chat

### Phase 7: Support Services (Month 6)

- [ ] notification-service (Email, SMS, Push)
- [ ] file-service (File Storage, Upload)
- [ ] report-service (Advanced Reporting)

**Deliverable**: Complete support infrastructure

### Phase 8: Production Ready (Month 7+)

- [ ] Comprehensive testing (unit, integration, E2E)
- [ ] Performance optimization
- [ ] Security hardening
- [ ] Kubernetes deployment manifests
- [ ] CI/CD pipeline
- [ ] Monitoring and alerting
- [ ] Documentation

**Deliverable**: Production-ready system

---

## 🧪 Testing

### Testing Strategy

Each microservice includes:

1. **Unit Tests** - Test individual components in isolation
   - Domain logic
   - Use cases
   - Utilities

2. **Integration Tests** - Test integration with external systems
   - Database interactions (using Testcontainers)
   - REST API endpoints
   - Message queues

3. **Contract Tests** - Ensure API compatibility between services
   - Consumer-driven contracts
   - OpenAPI specification validation

4. **End-to-End Tests** - Test complete user flows
   - Multi-service workflows
   - Authentication flows
   - Business processes

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for a specific service
./gradlew :infrastructure:auth-service:test

# Run integration tests only
./gradlew integrationTest

# Run with coverage report
./gradlew test jacocoTestReport
```

### Test Technologies

- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework
- **AssertJ** - Fluent assertions
- **Testcontainers** - Integration testing with Docker
- **Spring Security Test** - Security testing utilities
- **WireMock** - HTTP mocking
- **ArchUnit** - Architecture testing

---

## 📊 Monitoring & Observability

### Metrics (Prometheus)

Each service exposes metrics at `/actuator/prometheus`:

- **JVM Metrics** - Memory, GC, threads
- **HTTP Metrics** - Request count, latency, errors
- **Database Metrics** - Connection pool, query performance
- **Custom Business Metrics** - Orders created, revenue, etc.

### Visualization (Grafana)

Pre-built dashboards for:
- Service health overview
- Request rates and latencies
- Error rates
- Database performance
- JVM monitoring

### Distributed Tracing (Zipkin)

Trace requests across services to identify bottlenecks:

```
User Request → API Gateway → Auth Service → User Service
   [100ms]        [20ms]         [30ms]         [40ms]
```

### Centralized Logging (ELK Stack)

All logs are aggregated to Elasticsearch:

```json
{
  "timestamp": "2024-01-28T10:30:00Z",
  "service": "crm-service",
  "level": "INFO",
  "traceId": "abc123",
  "spanId": "def456",
  "message": "Contact created successfully",
  "userId": 123,
  "tenantId": 1
}
```

---

## 🐳 Docker & Kubernetes

### Docker Compose (Development)

```bash
# Start all infrastructure services
docker-compose up -d

# Start specific services
docker-compose up -d postgres redis rabbitmq

# Stop all services
docker-compose down

# View logs
docker-compose logs -f service-name
```

### Kubernetes (Production)

```bash
# Create namespace
kubectl create namespace escala-erp

# Deploy infrastructure
kubectl apply -f kubernetes/infrastructure/

# Deploy services
kubectl apply -f kubernetes/services/

# Check status
kubectl get pods -n escala-erp

# View logs
kubectl logs -f pod-name -n escala-erp
```

---

## 📚 API Documentation

API documentation is automatically generated using **OpenAPI 3.0** (Swagger).

### Access Swagger UI

Each service exposes Swagger UI at:
```
http://localhost:{port}/swagger-ui.html
```

Example:
- Auth Service: http://localhost:8081/swagger-ui.html
- User Service: http://localhost:8082/swagger-ui.html
- CRM Service: http://localhost:8083/swagger-ui.html

### OpenAPI Specification

Raw OpenAPI spec available at:
```
http://localhost:{port}/v3/api-docs
```

---

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

### Development Workflow

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/my-feature`
3. **Commit your changes**: `git commit -m "feat: add my feature"`
4. **Push to the branch**: `git push origin feature/my-feature`
5. **Open a Pull Request**

### Commit Message Convention

We follow [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `style:` - Code style changes (formatting, etc.)
- `refactor:` - Code refactoring
- `test:` - Adding or updating tests
- `chore:` - Build process or auxiliary tool changes

### Code Style

- Follow Java naming conventions
- Use Lombok to reduce boilerplate
- Write meaningful comments
- Keep methods small and focused
- Follow Clean Architecture principles

### Pull Request Checklist

- [ ] Code follows the project's coding standards
- [ ] Tests are included and passing
- [ ] Documentation is updated
- [ ] Commit messages follow the convention
- [ ] Branch is up-to-date with main

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Team

- **Project Lead**: [Alex Coronell]
- **Backend Team**: [Alex Coronell]
- **Frontend Team**: [Alex Coronell]
- **DevOps**: [Alex Coronell]

---

## 📞 Support

For questions or support:

- **Email**: contact@alexcoronell.dev
- **Slack**: [Slack Workspace]
- **Issues**: [GitHub Issues](https://github.com/alexcoronell/escala-erp/issues)
- **Documentation**: [Wiki](https://github.com/alexcoronell/escala-erp/wiki)

---

## 🗺️ Roadmap

### 2026 Q1
- ✅ Project setup and architecture design
- 🚧 Infrastructure services implementation
- 🚧 Authentication and user management

### 2026 Q2
- 📅 CRM module
- 📅 Sales module
- 📅 Inventory module

### 2026 Q3
- 📅 Accounting module
- 📅 Purchase module
- 📅 HR module

### 2026 Q4
- 📅 E-commerce module
- 📅 Chat module
- 📅 Support services

### 2027+
- 📅 Mobile applications
- 📅 Advanced analytics and BI
- 📅 AI-powered features
- 📅 Multi-language support
- 📅 Third-party integrations (Salesforce, SAP, etc.)

---

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- Netflix OSS for Eureka and other tools
- The open-source community

---

<p align="center">
  <strong>Built with ❤️ by the Escala ERP Team</strong>
</p>

<p align="center">
  <a href="https://github.com/alexcoronell/escala-erp">⭐ Star us on GitHub</a>
</p>
