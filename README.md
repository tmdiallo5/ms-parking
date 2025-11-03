# ms-parking

Parking reservation backend built with Spring Boot.  
Users can create parkings, define parking spots and book a spot for a given time.


## Features

- Create and manage parkings
- Assign `PARKING_MANAGER` role when a user creates a parking
- Manage parking spots per parking
- Book a parking spot for a specific time period


## Tech stack

- Java, Spring Boot
- Spring Security (JWT)
- Spring Data JPA / Hibernate
- MySQL
- Flyway for database migrations


## Getting started

1. Clone the repository  
   `git clone https://github.com/…/ms-parking.git`
2. Create a MySQL database `db-parking`.
3. Configure your DB credentials in `application-local.yml`.
4. Run the application  
   `mvn spring-boot:run`
5. API will be available at `http://localhost:8080`.
