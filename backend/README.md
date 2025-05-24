📚 Library Management System - Backend
Spring Boot REST API for library operations with JWT security and MySQL integration

🎯 Backend Responsibilities
RESTful API for frontend interactions

JWT Authentication & Authorization (Librarian/Member roles)

Business Logic Enforcement:

Lending rules (max books, renewals, fines)

Membership validity checks

Database Operations via Spring Data JPA

🚀 Tech Stack
Layer	Technology
Framework	Spring Boot 3.x
Security	Spring Security + JWT
Database	MySQL 8
ORM	Hibernate/JPA
Build Tool	Maven
📂 API Endpoints (Key Examples)
🔐 Auth Controller
POST /api/auth/register → Register (Librarian/Member)

POST /api/auth/login → JWT Token generation

POST /api/auth/reset-password → Password reset

📖 Book Controller
GET /api/books?search={query}&status={available} → Search/filter books

POST /api/books → Add new book (Librarian only)

PUT /api/books/{id}/reserve → Reserve book (Member)

📦 Lending Controller
POST /api/loans/borrow → Borrow book (with due date calc)

POST /api/loans/return → Return book (+ fine if overdue)

GET /api/loans/member/{id} → Member’s borrowing history

⚙️ Setup & Configuration
1. Database Setup
   CREATE DATABASE lms_db;
3. Configure application.properties:
#Application
spring.application.name=library-management-system
server.port=8080
server.servlet.context-path=/api  # Adds /api prefix to all endpoints

#database
spring.datasource.url=jdbc:mysql://localhost:3306/lms_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#Hibernate/JPA
spring.jpa.hibernate.ddl-auto=update  # Change to 'validate' in production
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

#SECURITY
# JWT Configuration (adjust values)
app.jwt.secret=X1EIpjSNiogtwgxKpfCIIuEv1KjIE59phKQRY7CfO44=
app.jwt.expiration-ms=86400000

# BUSINESS RULES 
lms.max-books-per-member=3
lms.loan-duration-days=14
lms.fine-per-day=0.50
lms.max-fine-per-book=20.00

4. Run the Backend
./mvnw spring-boot:run   # macOS/Linux
mvnw.cmd spring-boot:run # Windows
API Docs: http://localhost:8080/swagger-ui.html

🔐 Enforced Business Rules
✅ Membership Validity: 1-year expiration from registration date.

⚠️ Borrowing Limits:
Max 3 books per member.
Block if fines > $10 or overdue books exist.

💰 Fine System:
$0.50/day after due date, capped at $20/book.
Fines start day after due date.

📜 Integration with Frontend
Headers: Send JWT token in Authorization: Bearer {token}.
CORS: Pre-configured for http://localhost:3000 (React).
