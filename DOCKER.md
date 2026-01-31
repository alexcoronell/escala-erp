# Docker Infrastructure - Escala ERP

Local development infrastructure using Docker Compose.

## 🚀 Quick Start

```bash
# Start all services
docker-compose up -d

# Check status
docker-compose ps

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Stop and remove volumes (⚠️ deletes all data)
docker-compose down -v
```

## 📦 Services

| Service         | Port | URL                   | Credentials                     |
|-----------------|------|-----------------------|---------------------------------|
| PostgreSQL      | 5432 | localhost:5432        | postgres / postgres             |
| PostgreSQL Test | 5433 | localhost:5433        | test / test123                  |
| Redis           | 6379 | localhost:6379        | password: redis123              |
| PgAdmin         | 5050 | http://localhost:5050 | admin@escala-erp.com / admin123 |
| Redis Commander | 8081 | http://localhost:8081 | -                               |

## 🗄️ PostgreSQL

**Database:** `escala_erp`

**Schemas created automatically:**
- `auth` - Authentication service
- `users` - User management service
- `crm` - CRM service
- `inventory` - Inventory service
- `sales` - Sales service
- `purchases` - Purchase service
- `accounting` - Accounting service
- `hr` - Human Resources service
- `projects` - Project management service
- `reports` - Reporting service
- `notifications` - Notification service
- `assets` - Asset management service
- `workflow` - Workflow service
- `audit` - Audit service
- `settings` - Settings service

**Connection string:**
```
jdbc:postgresql://localhost:5432/escala_erp
```

## 💾 Redis

**Databases:**
- 0: auth-service (tokens, sessions)
- 1: user-service (user cache)
- 2: crm-service (customer cache)
- 3-15: Available for other services

**Password:** `redis123`

## 🔧 PgAdmin

Access at http://localhost:5050

**Pre-configured servers:**
1. Escala ERP - Development (postgres:5432)
2. Escala ERP - Test (postgres-test:5432)

## 📊 Redis Commander

Access at http://localhost:8081

Visual interface for Redis management.

## 🔍 Health Checks

```bash
# PostgreSQL
docker exec escala-erp-postgres pg_isready -U postgres

# Redis
docker exec escala-erp-redis redis-cli -a redis123 ping

# All services
docker-compose ps
```

## 📂 Data Persistence

Volumes:
- `escala-erp-postgres-data` - PostgreSQL data
- `escala-erp-redis-data` - Redis data
- `escala-erp-pgadmin-data` - PgAdmin configuration

**Backup data:**
```bash
docker run --rm -v escala-erp-postgres-data:/data -v $(pwd):/backup alpine tar czf /backup/postgres-backup.tar.gz /data
```

**Restore data:**
```bash
docker run --rm -v escala-erp-postgres-data:/data -v $(pwd):/backup alpine tar xzf /backup/postgres-backup.tar.gz -C /
```

## 🛠️ Troubleshooting

**Port already in use:**
```bash
# Change ports in docker-compose.yml
ports:
  - "15432:5432"  # PostgreSQL
  - "16379:6379"  # Redis
```

**Reset everything:**
```bash
docker-compose down -v
docker-compose up -d
```

**View logs:**
```bash
docker-compose logs postgres
docker-compose logs redis
```

## Author

Alex Coronell | 2024-01-28
