# 🚗 Ride Sharing Console Application (Java + JDBC)

## 📌 Project Overview
This project is a backend-based Ride Sharing Application developed using Java and JDBC.
It simulates core functionalities of real-world ride-sharing platforms like Uber/Ola but runs as a console application.

---

## 🛠 Technologies Used
- Java (Core Java, OOP)
- JDBC
- MySQL Database
- SQL

---

## 🧩 Features

### 👤 User System
- User Registration
- Login System
- Email format validation
- Duplicate email prevention
- Role-based system (Driver / Passenger)

### 🚘 Driver Features
- Only drivers with valid license can create rides
- License format validation (Example: DL12345)
- Ride creation with source, destination, seats and fare

### 🧍 Passenger Features
- View available rides
- Book rides
- Seat availability check
- Prevents overbooking

### 🎯 Ride Management
- Tracks total seats and available seats
- Reduces seats after booking
- Shows seats left

### 🔐 Validation
- Email format check
- Unique email
- License format check
- Role-based access control

---

## 🗄 Database Tables

### users
user_id, name, email, password, role, license

### rides
ride_id, source, destination, total_seat, available_seats, fare, driver_id

### bookings
booking_id, user_id, ride_id, seats_booked

---

## ⚙️ How to Run

1. Start MySQL Server
2. Create database and tables using SQL script
3. Place `mysql-connector-j-9.6.0.jar` in project folder
4. Update MySQL password in DBConnection.java

Compile:
- javac -cp ".;mysql-connector-j-9.6.0.jar" *.java
Run:
- java -cp ".;mysql-connector-j-9.6.0.jar" Main


---

## 🧠 Architecture
Model Classes → User, Ride, Booking  
Service Layer → Business logic + SQL  
DBConnection → Database connectivity  
Main → Application flow

---

## 🚀 Learning Outcomes
- OOP in Java
- JDBC connectivity
- CRUD operations
- Validation logic
- Role-based backend system
- Seat management logic

---

## 🔮 Future Improvements
- Password encryption
- GUI/Web frontend
- Ride filters
- Ride cancellation
- Payment integration
