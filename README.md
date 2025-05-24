# 📚 Library Management System

A full-stack web application to manage library operations — built using **Spring Boot**, **React**, and **MySQL**.

## 🎯 Project Goals

- Enable librarians to manage books, members, and lending processes efficiently
- Allow members to search books, reserve, and track their borrowing history
- Apply lending rules, fine calculation, and reservation logic

## 🔑 Key Features

### 👤 User Management
- User registration and login (JWT)
- Librarian and Member roles
- Profile management
- Password reset functionality

### 👥 Member Management
- Register new members with personal details (name, address, contact information, etc.)
- Edit, delete members
- Search for members by name or ID

### 📖 Book Management
- Add, update, delete, view books
- Book attributes: ISBN, title, author, category, publicationvyear, copies available
- Search and filter books by various criteria
- Track book status (available, borrowed, reserved)

### 📦 Lending Management
- Borrow and return books
- View borrowing history
- Reserve books
- Due date tracking and automatic fine calculation

## ⚙️ Tech Stack

| Layer        | Technology              |
|--------------|--------------------------|
| Frontend     | React, React Router, Axios, Bootstrap |
| Backend      | Spring Boot, RESTful API, Spring Security, JPA (Hibernate) |
| Database     | MySQL                    |
| Auth         | JWT (JSON Web Tokens)    |
| Version Ctrl | Git & GitHub             |

## 🔐 Business Rules

- Membership valid for 1 year from registration
- Max 3 books per member at a time
- Loan duration: 14 days, with max 2 renewals
- Overdue fine: $0.50 per day, capped at $20 per book
- Fine starts accumulating from day after due date
- Borrowing is blocked if fines > $10 or if having any overdue books

## 🧪 Deliverables & Criteria

- All core features implemented
- JWT authentication & role-based access control
- Proper error handling and validation
- Functional and tested UI/UX
- Technical documentation included
- No critical bugs

## 🎯 Target Audience

- Librarians: To efficiently manage library resources.
- Members: To easily search for books and track their borrowing history

## 🚀 Getting Started (Development)

### Backend
```bash
cd backend
./mvnw spring-boot:run
