# PeepPeaHotel Project Documentation

## Overview

PeepPeaHotel is a hotel management application that allows users to manage rooms, reservations, and guest data. The system includes functionality for viewing available rooms, making reservations, and tracking occupancy on specific dates. This documentation provides an overview of the core files and their responsibilities in the `web`, `service`, and `data` packages.

---

## Web Layer

The **web** package contains both **API controllers** and **normal controllers** that handle HTTP requests and responses. 

### Difference Between API Controllers and Normal Controllers:
- **API Controllers**: Provide RESTful endpoints, typically returning JSON responses and are used for external interactions (e.g., with a front-end or third-party services).
- **Normal Controllers**: Render HTML views using server-side templates, used to return fully rendered pages to be viewed in a browser.

### API Controllers

These controllers expose endpoints for performing CRUD (Create, Read, Update, Delete) operations via REST APIs, typically returning data in JSON format.

1. **GuestApiController**
   - Manages API endpoints for creating, retrieving, updating, and deleting guest records.
   - Handles requests such as `GET /api/guests`, `POST /api/guests`, and `DELETE /api/guests/{id}`.
   - Returns JSON responses and interacts with the `GuestRepository` to handle guest-related operations.

2. **ReservationApiController**
   - Provides API endpoints for managing room reservations.
   - Handles requests like `GET /api/reservations` (with an optional date filter), `POST /api/reservations`, and `DELETE /api/reservations/{id}`.
   - Returns JSON data about reservations and interacts with `ReservationRepository`.

3. **RoomApiController**
   - Manages room-related API endpoints.
   - Allows creating, updating, retrieving, and deleting room records.
   - Handles `GET /api/rooms`, `POST /api/rooms`, and `GET /api/rooms/{id}`.
   - Returns JSON data about room information and interacts with the `RoomRepository`.

### Normal Controllers

These controllers are responsible for rendering views in the browser using server-side templates. They return fully rendered HTML pages and are typically used in server-side rendered web applications.

1. **OccupancyController**
   - Handles the room occupancy view, providing an overview of reserved rooms for a specific date.
   - Manages the `GET /occupancy` endpoint and renders the `occupancy.html` view.
   - Populates the view with room reservation data for the given date by interacting with the `RoomReservationService`.

2. **RoomController**
   - Displays available and reserved rooms.
   - Handles `GET /rooms` to display all rooms and `GET /rooms/reserved` to show reserved rooms on a specific date.
   - Renders `room-list.html` for showing all rooms and `reserved-room-list.html` for reserved rooms on a given date.

3. **WelcomeController**
   - A simple controller that provides a customizable greeting message.
   - Handles the `GET /welcome` endpoint and returns a greeting in HTML format.
   - This controller dynamically generates an HTML greeting based on a query parameter (`name`).

---

## Service Layer

The **service** package contains the business logic of the application, primarily focused on managing reservations and providing room availability information.

### Services

1. **RoomReservationService**
   - Provides methods to retrieve room reservation details for specific dates.
   - Main Method: `getRoomReservationsForDate(String reservationDate)` - Fetches room reservations for a given date and populates guest and room information.
   - Works with `GuestRepository`, `RoomRepository`, and `ReservationRepository` to gather the necessary data.

### Models

1. **RoomReservation**
   - A model class used to transfer room and reservation information between layers.
   - Contains details such as room ID, room name, guest ID, guest name, reservation ID, and reservation date.

---

## Data Layer

The **data** package is responsible for interacting with the database. It contains entity classes that represent database tables and repositories that handle CRUD operations for those entities.

### Entities

1. **Guest**
   - Represents a hotel guest.
   - Fields include guest ID, first name, last name, email address, address, country, state, and phone number.
   - Mapped to the `guests` table in the database.

2. **Room**
   - Represents a hotel room.
   - Fields include room ID, room name, room number, and bed information.
   - Mapped to the `rooms` table in the database.

3. **Reservation**
   - Represents a reservation made by a guest for a specific room on a specific date.
   - Fields include reservation ID, room ID, guest ID, and reservation date.
   - Mapped to the `reservations` table in the database.

### Repositories

1. **GuestRepository**
   - Handles database operations for the `Guest` entity.
   - Extends `JpaRepository<Guest, Long>`, allowing CRUD operations and custom queries for guests.

2. **RoomRepository**
   - Handles database operations for the `Room` entity.
   - Extends `JpaRepository<Room, Long>`, enabling CRUD operations and providing a method to find rooms by room number (`findByRoomNumberIgnoreCase`).

3. **ReservationRepository**
   - Manages database interactions for the `Reservation` entity.
   - Extends `JpaRepository<Reservation, Long>` and provides a custom method `findAllByReservationDate` to retrieve reservations by date.

---

## Conclusion

The **PeepPeaHotel** project follows a layered architecture with clear separation between the web layer (controllers), service layer (business logic), and data layer (repositories and entities). The controllers handle HTTP requests, delegate the business logic to services, and interact with repositories to fetch or modify data in the database. The service layer processes the business logic for reservations and room availability, while the data layer manages the persistence of guest, room, and reservation information.

This documentation provides a high-level understanding of the project structure and the functionality of each key component.


## To Start Docker

1. docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=local -d -p 5432:5432 -e PGDATA=/var/lib/postgresql/data/pgdata -v "${env:USERPROFILE}\.local\docker\postgresql\data:/var/lib/postgresql/data" postgres


2. chcp 1252
3. psql -h localhost -p 5432 -U postgres -d local
4. \dt lil.*


## PostMan

    PeepPeaHotel

    - GET Rooms
    http://localhost:8080/api/rooms

    - GET Room 7
    http://localhost:8080/api/rooms

    - GET Guests
    http://localhost:8080/api/guests

    - GET Guest 33
    http://localhost:8080/api/guests/33

    -GET Reservation
    http://localhost:8080/api/reservations
