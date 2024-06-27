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