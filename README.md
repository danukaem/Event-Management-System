# Event Management System

A scalable backend application for managing events, built using **Spring Boot** and **Java**.

## Features

- User authentication and authorization using JWT.
- Role-based access control (Admin, User, etc.).
- Event creation, management, and retrieval.
- RESTful API design.
- Input validation using `spring-boot-starter-validation`.
- Rate limiting using `Bucket4j`.
- Integration with PostgreSQL for data persistence.

## Technologies Used

- **Java 21**
- **Spring Boot 3.4.5**
- **PostgreSQL**
- **JWT (JSON Web Tokens)** for authentication.
- **Bucket4j** for rate limiting.
- **Lombok** for reducing boilerplate code.
- **Maven** for dependency management.

## Prerequisites

- Java 21 or higher
- Maven 3.9.9 or higher
- PostgreSQL database

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/EventManagementSystem.git
cd EventManagementSystem

Configure the Database
Update the database configuration in src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/event_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

Create a PostgreSQL database named event_management.
Build and Run the Application
Build the project using Maven:

mvn clean install

Run the application:
mvn spring-boot:run

The application will start on http://localhost:8080.


API Endpoints
Authentication
POST /auth/login - Authenticate a user and retrieve a JWT.
{
    "username": "test_new_user_4",
    "password": "111"
}

POST /auth/register - Register a new user.
{
    "username": "test_new_user",
    "password": "111",
    "role": "USER",
    "email": "abc@gmail.com"
}

Events
POST /api/v1/events - Create a new event (requires authentication).
{
    "title": "test_event_14",
    "description": "event14 description",
    "location": "House",
    "visibility": "PRIVATE",
    "startTime": "2025-05-17T10:30:00",
    "endTime": "2025-05-17T12:30:00",
    "hostId": "f7303a5e-1e07-437e-b34a-7844abbf5dfa",
    "attendances": [] 

}

PUT /api/v1/events/<eventId> - Update Events
{
    "title": "test_event_9",
    "description": "event9 description updated 3.04",
    "location": "House",
    "visibility": "PRIVATE",
    "startTime": "2025-05-17T10:30:00",
    "endTime": "2025-05-17T12:30:00",
    "hostId": "f7303a5e-1e07-437e-b34a-7844abbf5dfa",
    "attendances": [] 
}

DELETE /api/v1/events/<eventId>

POST /api/v1/attendance - Create Attendance
{
    "userId": "e07160a2-46f0-48f6-8a4c-1706479e700e",
    "eventId": "e04c88a9-3466-4321-b3eb-af2f84427800",
    "status": "MAYBE",
    "respondedAt": "2025-05-17T11:30:00" 
}

GET /api/v1/events/upcoming?page=0&size=4  - List upcoming events (paginated)

GET /api/v1/events - Retrieve all events (requires authentication).

GET /api/v1/events/<event_id>/status - Status check of an event

GET /api/v1/events/<user_id>/events - List all events a user is hosting or attending

GET /api/v1/events/location/Office - List events with filtering by date, location, visibility

GET /api/v1/events/<event_id> - Get event details with attendee count

Testing
Run the tests using:
mvn test

Security
JWT-based authentication is implemented.
Endpoints are secured using Spring Security.
CSRF is disabled for simplicity.
Project Structure
src/main/java/com/assignment/EventManagementSystem - Main application code.
src/main/resources - Configuration files.
src/test/java/com/assignment/EventManagementSystem - Test cases.
Dependencies
Key dependencies used in the project:


Spring Boot Starter Web: For building RESTful APIs.
Spring Boot Starter Security: For authentication and authorization.
Spring Boot Starter Validation: For input validation.
PostgreSQL Driver: For database connectivity.
Bucket4j: For rate limiting.
Lombok: To reduce boilerplate code.
License
This project is licensed under the Apache License 2.0. See the LICENSE file for details.


Author
Developed by Danuka.