# Exam Seating Arrangement System

A Spring Boot web app for managing students and assigning exam seating by room and seat number.

## Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven

## Run Locally

Create a MySQL database named `examdb`, then run the app with your database settings:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/examdb"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your-password"
mvn spring-boot:run
```

The app runs on `http://localhost:8080`.
