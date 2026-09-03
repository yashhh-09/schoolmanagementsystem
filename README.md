# School Management System - Spring Boot Backend

## Stack
- Java 17
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA / Hibernate
- Spring Security + JWT
- MySQL
- Swagger / OpenAPI
- Maven

## Run

1. Create MySQL database (optional because the JDBC URL can create it):
   `CREATE DATABASE school_management;`

2. Open `src/main/resources/application.properties` and change:
   `spring.datasource.username`
   `spring.datasource.password`

3. Run:
   `mvn clean spring-boot:run`

Or open the project in IntelliJ IDEA and run `SchoolManagementApplication`.

## Default login
Username: `admin`
Password: `admin123`

Login:
POST `http://localhost:8080/api/auth/login`

Body:
```json
{
  "username": "admin",
  "password": "admin123"
}
```

Copy the JWT token from the response and send it as:
`Authorization: Bearer <token>`

## Swagger
`http://localhost:8080/swagger-ui.html`

## Main APIs
- `/api/auth`
- `/api/students`
- `/api/teachers`
- `/api/classes`
- `/api/subjects`
- `/api/attendance`
- `/api/exams`
- `/api/results`
- `/api/fees`
- `/api/library/books`
- `/api/library/issues`
- `/api/notices`
- `/api/transport`
- `/api/hostel`

## Example student
POST `/api/students`
```json
{
  "admissionNumber": "STU001",
  "firstName": "Rahul",
  "lastName": "Patel",
  "email": "rahul@gmail.com",
  "phone": "9876543210",
  "gender": "MALE",
  "dateOfBirth": "2010-05-20",
  "address": "Ahmedabad",
  "parentName": "Raj Patel",
  "parentPhone": "9876500000",
  "className": "10",
  "section": "A"
}
```
