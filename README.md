# ParkSlotPilot - Backend (Spring Boot)

This is the backend API for ParkSlotPilot, a parking reservation application.

---

##  Current Features

- User registration
- Email-based account activation
- JWT authentication
- Secure login system
- Role-based access (CLIENT)

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate

---

## Authentication Flow

1. User signs up
2. Receives activation email
3. Activates account via token
4. Logs in and receives JWT

---

## API Endpoints

- `POST /api/sign-up`
- `GET /api/auth/activate`
- `POST /api/sign-in`

---

## Upcoming Features

- Parking search by location and time
- Booking system
