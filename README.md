# Microservices Hotel Reservation

A small **Hotel Reservation Microservices** project built with Spring Boot to practice core microservices concepts and distributed communication.

## Architecture

```text
Client
   ↓
API Gateway :8090
   ↓
┌───────────────────────┐
│                       │
Hotel Service       Booking Service
   :8080                :8081
                         ↓
                      Kafka
                         ↓
               Notification Service
                      :8082
                         ↓
                   Hotel Service
                      :8080
```

## Technologies

* Java & Spring Boot
* Spring Cloud Gateway
* Apache Kafka
* Keycloak / JWT
* MySQL
* Flyway
* Resilience4j
* OpenAPI / Swagger
* Docker

## Concepts Practiced

* Microservice architecture
* API Gateway & routing
* REST service-to-service communication
* Kafka event-driven communication
* JWT authentication with Keycloak
* Circuit Breaker
* Database migrations with Flyway
* DTOs & Mappers
* Global exception handling
* OpenAPI / Swagger

## API Documentation

Gateway Swagger UI:

```text
http://localhost:8090/swagger-ui/index.html
```

Hotel Service Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

Booking Service Swagger:

```text
http://localhost:8081/swagger-ui/index.html
```

## Purpose

Built as a **hands-on learning project** to understand how Spring Boot microservices communicate, handle failures, secure APIs, and process asynchronous events.
