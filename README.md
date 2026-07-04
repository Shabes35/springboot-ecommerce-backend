# 🛒 Spring Boot E-Commerce Backend

A RESTful E-Commerce Backend application built using Spring Boot. This project demonstrates backend development concepts including JWT Authentication, Spring Security, CRUD operations, shopping cart, order management, pagination, search, exception handling, and Swagger API documentation.

---

## 🚀 Features

- User Registration
- User Login with JWT Authentication
- Spring Security Protected APIs
- Product CRUD Operations
- Category Management
- Shopping Cart Management
- Order Placement
- Pagination & Sorting
- Product Search
- Global Exception Handling
- Swagger/OpenAPI Documentation

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger / OpenAPI
- Lombok

---

## 📁 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 └── service
```

---

## 🔐 Authentication

This project uses JWT Authentication.

### Public Endpoints

```
POST /users/register
POST /auth/login
```

### Protected Endpoints

All other APIs require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📦 Main Modules

### User

- Register User
- Login
- JWT Authentication

### Product

- Create Product
- Update Product
- Delete Product
- Get Product by ID
- Search Products
- Pagination & Sorting

### Category

- Create Category
- Update Category
- Delete Category
- View Categories

### Cart

- Create Cart
- Add Cart Items
- View Cart
- Calculate Cart Total

### Order

- Place Order
- View Orders
- Update Order Status
- View Order Items
- Calculate Order Total

---

## 📄 API Documentation

Swagger UI is available after running the project:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚙️ How to Run

### Clone Repository

```bash
git clone https://github.com/Shabes35/springboot-ecommerce-backend.git
```

### Configure Database

Update the following in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run Project

```bash
mvn spring-boot:run
```

---

## 📸 Screenshots

You can add screenshots here later.

Example:

- Swagger UI
- Login API
- Product APIs
- Order APIs

---

## 📚 Concepts Learned

- Spring Boot REST APIs
- Layered Architecture
- Dependency Injection
- DTO Pattern
- Exception Handling
- Spring Data JPA
- Entity Relationships
- JWT Authentication
- Spring Security
- Swagger Documentation
- Git & GitHub

---

## 🔮 Future Improvements

- Role-Based Authorization (ADMIN / USER)
- Refresh Tokens
- Unit Testing
- Docker Support
- Deployment to Cloud
- Cart & Order Ownership Validation

---

## 👨‍💻 Author

**Shabes R**

GitHub:
https://github.com/Shabes35