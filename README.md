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
- Add, edit, delete, and search members
- View member details

### 📖 Book Management
- Add, update, delete, view books
- Book attributes: ISBN, title, author, category, year, copies
- Search and filter functionality

### 📦 Lending Management
- Borrow and return books
- View borrowing history
- Reserve books
- Due date tracking and automatic fine calculation

## ⚙️ Tech Stack

| Layer        | Technology              |
|--------------|--------------------------|
| Frontend     | React, React Router, Axios, Bootstrap |
| Backend      | Spring Boot, Spring Security, JPA (Hibernate) |
| Database     | MySQL                    |
| Auth         | JWT (JSON Web Tokens)    |
| Version Ctrl | Git & GitHub             |

## 🔐 Business Rules

- Max 3 books per member
- Loan duration: 14 days, with max 2 renewals
- Overdue fine: $0.50 per day, capped at $20
- Borrowing is blocked if fines > $10 or books are overdue

## 🧪 Deliverables & Criteria

- All core features implemented
- JWT authentication & role-based access control
- Proper error handling and validation
- Functional and tested UI/UX
- Technical documentation included

## 🚀 Getting Started (Development)

### Backend
```bash
cd backend
./mvnw spring-boot:run
