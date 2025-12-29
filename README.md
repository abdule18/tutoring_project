# Tutoring Management System – Backend API

## Overview
This is a personal backend project built with Spring Boot that provides a RESTful API for managing a tutoring system. The application supports students, tutors, subjects, rooms, enrollments, and tutoring appointments, with data persisted in a relational database using Spring Data JPA.

The project follows real-world backend development practices, including layered architecture, DTO usage, service-based business logic, and clean separation of concerns.

## Tech Stack
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Jakarta Bean Validation
- Maven

## Architecture
The application follows a layered architecture:

Controller → Service → Repository → Database

- Controllers expose REST endpoints
- Services contain business logic
- Repositories handle database access
- Entities represent database tables
- DTOs define request and response payloads
- Custom exceptions handle domain-specific errors

## Core Features
- Create, read, update, and delete students
- Create and manage tutors and their subjects
- Create and manage subjects and rooms
- Enroll students in subjects with uniqueness enforcement
- Schedule tutoring appointments
- Link appointments to students, tutors, subjects, and rooms
- Validate request data using annotations
- Persist data using JPA and PostgreSQL

## Domain Models
- Student
- Tutor
- Subject
- Room
- Enrollment
- Appointment

Appointments connect a student, tutor, subject, and room and include start time, end time, and appointment status.

## API Endpoints

### Students
GET /api/v1/student  
GET /api/v1/student/{id}  
POST /api/v1/student  
PUT /api/v1/student/{id}  
DELETE /api/v1/student/{id}  

### Tutors
GET /api/v1/tutor  
POST /api/v1/tutor  

### Subjects
GET /api/v1/subject  
POST /api/v1/subject  
DELETE /api/v1/subject/{id}  

### Rooms
POST /api/v1/room  

### Appointments
GET /api/v1/appointment  
POST /api/v1/appointment  

### Enrollments
GET /api/v1/enrollment  

## Sample Request

POST /api/v1/student
Content-Type: application/json

{
  "firstName": "Noon",
  "lastName": "Boon",
  "email": "noonboon@gmail.com",
  "password": "12345"
}

## Configuration
Application configuration is handled in application.properties, including database connection and JPA settings.

Example:

spring.datasource.url=jdbc:postgresql://localhost:5434/postgres  
spring.datasource.username=your_username  
spring.datasource.password=your_password  
spring.jpa.hibernate.ddl-auto=update  

## Running the Application
1. Clone the repository
2. Ensure PostgreSQL is running
3. Update database credentials in application.properties
4. Run the application using Maven:

mvn spring-boot:run

5. The API will be available at:

http://localhost:8080

## Testing
The API endpoints were tested using HTTP requests (GET, POST, PUT, DELETE) via an HTTP client. Successful requests return standard HTTP status codes such as 200 and 201.

## Project Status
Complete. The backend is fully functional and designed as a reusable foundation for a tutoring or scheduling system.

## Author
Abdule Touray
