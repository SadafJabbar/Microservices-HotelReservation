# Microservices Hotel Reservation

A small **Hotel Reservation Microservices** project built with Spring Boot to practice core microservices concepts and distributed communication.

## Architecture

                    Client
                      │
                      ▼
              API Gateway :8090
                 │          │
                 ▼          ▼
          Hotel Service   Booking Service
             :8080           :8081
                                │
                   ┌────────────┴────────────┐
                   │                         │
                   ▼                         ▼
             Hotel Service                 Kafka
                :8080                        │
                                             ▼
                                  Notification Service
                                         :8082

### Booking Process

1. **Client sends a booking request** to the API Gateway.
2. **API Gateway forwards the request** to the Booking Service.
3. **Booking Service contacts the Hotel Service** to verify the hotel and room availability.
4. **Hotel Service returns the result** to the Booking Service.
5. **Booking Service creates the booking** if the room is available.
6. **Booking Service contacts the Hotel Service** to update the room status.
7. **Booking Service publishes a booking event** to Kafka.
8. **Kafka delivers the event** to the Notification Service.
9. **Notification Service processes the event** and sends the notification.

**Key communication:**
`Booking Service → Hotel Service` for room operations
`Booking Service → Kafka → Notification Service` for booking events

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
### Redis Caching

* Added Redis caching to **Hotel** and **Booking Services**.
* Used `@Cacheable` for frequently accessed data.
* Configured **TTL** for automatic cache expiration.
* Redis runs through Docker.

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
