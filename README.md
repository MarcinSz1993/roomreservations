## This is a backend project which simulate a platform to manage reservations like create rooms, reservations, register guests, add, search, edit and remove these things.
### In the project I used Java, Spring Boot, Postgresql.<br><br>

### Basic operations in this app:

First you need to register your account. To do it go to below endpoint:
```http request
http://localhost:8080/guests/
```

and fill required fields in:
```json
{
  "name":"Katarzyna",
  "surname":"Kowalska",
  "birthDate":"1997-08-13",
  "email":"kkowalska@example.pl",
  "password":"qwerty",
  "phoneNumber":"12345678"
}
```
When your request is correct you should get a response:
```json
{
    "id": 1,
    "name": "Katarzyna",
    "surname": "Kowalska",
    "dateOfBirth": "1997-08-13",
    "email": "kkowalska@example.pl",
    "phoneNumber": "12345678",
    "reservations": []
}
```

When you are registered then you can log in with endpoint:
```http request
http://localhost:8080/guests/login
```
and typing email and password in the request:
```json
{
    "email":"kkowalska@example.pl",
    "password":"qwerty"
}
```
When everything is fine with logging in, you should see a token in the response:
```json
{
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJra293YWxza2FAZXhhbXBsZS5wbCIsImlhdCI6MTcyMjM1NjEwMCwiZXhwIjoxNzIyNTI4OTAwfQ.4b1Uv3cj7ExHVNSxS8jxgRPxQFD1CEAcTrbHRKrUXnkoBRzBq2NkYLr1l5pkLQUS"
}
```

From now on, you can make a new reservation. However, before doing so, you have the option to check the availability of rooms on specific dates. This option allows you to select your preferred dates and view all available rooms along with their specific facilities.
```http request
http://localhost:8080/rooms/available
This endpoint requires 2 parameters:
startDate
endDate

Example:
http://localhost:8080/rooms/available?startDate=2024-08-30&endDate=2024-09-05
```
As the response you get list of rooms:
```json
[
    {
        "id": 1,
        "roomNumber": "1",
        "pricePerNight": 100.0,
        "capacity": 1,
        "hasHairDryer": false,
        "hasSauna": false,
        "hasPrivateBathroom": false,
        "hasAirConditioning": false,
        "hasBalcony": false,
        "available": true
    },
    {
        "id": 2,
        "roomNumber": "2",
        "pricePerNight": 200.0,
        "capacity": 2,
        "hasHairDryer": false,
        "hasSauna": false,
        "hasPrivateBathroom": false,
        "hasAirConditioning": false,
        "hasBalcony": false,
        "available": true
    },
    {
        "id": 3,
        "roomNumber": "3",
        "pricePerNight": 300.0,
        "capacity": 2,
        "hasHairDryer": true,
        "hasSauna": false,
        "hasPrivateBathroom": true,
        "hasAirConditioning": true,
        "hasBalcony": true,
        "available": true
    },
    {
        "id": 4,
        "roomNumber": "4",
        "pricePerNight": 500.0,
        "capacity": 3,
        "hasHairDryer": true,
        "hasSauna": true,
        "hasPrivateBathroom": true,
        "hasAirConditioning": true,
        "hasBalcony": false,
        "available": true
    },
    {
        "id": 5,
        "roomNumber": "5",
        "pricePerNight": 400.0,
        "capacity": 2,
        "hasHairDryer": true,
        "hasSauna": true,
        "hasPrivateBathroom": true,
        "hasAirConditioning": true,
        "hasBalcony": true,
        "available": true
    }
]
```
There is an additional useful method for selecting a room.
You can specify exactly what type of room you are looking for.
To do this, use the following endpoint:
```http request
http://localhost:8080/rooms/filtered
The endpoint requires 2 mandatory parameters:
startDate
endDate
Below you find optional parameters. Those parameters will help you to find the room you want:
capacity
hairDryer
sauna       
privateBathroom
airConditioning
balcony
```
Example request when you are interested in room for 2 persons included sauna, private bathroom and balcony looks like this:
```http request
http://localhost:8080/rooms/filtered?startDate=2024-08-10&endDate=2024-08-15&capacity=2&privateBathroom=true&balcony=true
```
As a response you will receive a list of rooms which meet your criteria:
```json
[
  {
    "id": 3,
    "roomNumber": "3",
    "pricePerNight": 300.0,
    "capacity": 2,
    "available": true,
    "hasHairDryer": true,
    "hasSauna": false,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
  },
  {
    "id": 5,
    "roomNumber": "5",
    "pricePerNight": 400.0,
    "capacity": 2,
    "available": true,
    "hasHairDryer": true,
    "hasSauna": true,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
  },
  {
    "id": 7,
    "roomNumber": "7",
    "pricePerNight": 450.0,
    "capacity": 2,
    "available": true,
    "hasHairDryer": true,
    "hasSauna": true,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
  },
  {
    "id": 10,
    "roomNumber": "10",
    "pricePerNight": 600.0,
    "capacity": 2,
    "available": true,
    "hasHairDryer": true,
    "hasSauna": true,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
  },
  {
    "id": 15,
    "roomNumber": "15",
    "pricePerNight": 280.0,
    "capacity": 2,
    "available": true,
    "hasHairDryer": true,
    "hasSauna": false,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
  },
  {
    "id": 23,
    "roomNumber": "23",
    "pricePerNight": 190.0,
    "capacity": 2,
    "available": true,
    "hasHairDryer": false,
    "hasSauna": false,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
  }
]
```
If you want to make a reservation go to endpoint:
```http request
http://localhost:8080/reservations/
```
and select number of room, dates and payment method in a request:
```json
{
    "roomNumber":"18",
    "startReservation":"2024-08-30",
    "endReservation":"2024-09-05",
    "paymentMethod":"CARD"
}
```
A response will be:
```json
{
    "id": 1,
    "reservedRoom": "18",
    "price": 1830.0,
    "paymentMethod": "CARD",
    "paymentStatus": "NOT_PAID",
    "startReservation": "2024-08-30",
    "endReservation": "2024-09-05"
}
```
As an admin you have more options to manage the application.
You can view list of all reservations in your system under this endpoint:
```http request
http://localhost:8080/reservations/all
```
You will receive list of ReservationDto:
```json
[
    {
        "id": 1,
        "guestSurname": "Kowalska",
        "guestEmail": "kkowalska@example.pl",
        "reservedRoom": "18",
        "price": 1830.0,
        "paymentMethod": "CARD",
        "paymentStatus": "NOT_PAID",
        "startReservation": "2024-08-30",
        "endReservation": "2024-09-05"
    },
    {
        "id": 2,
        "guestSurname": "Doe",
        "guestEmail": "johnny@example.pl",
        "reservedRoom": "26",
        "price": 300.0,
        "paymentMethod": "CASH",
        "paymentStatus": "NOT_PAID",
        "startReservation": "2024-10-20",
        "endReservation": "2024-10-23"
    }
]
```
You can also find a guest by surname using this endpoint:
```http request
http://localhost:8080/guests/surname
mandatory param is named 'surname'
Example:
http://localhost:8080/guests/surname?surname=Kowalska
```
In a response you will get info about guest and his reservations:
```json
{
    "id": 2,
    "name": "Katarzyna",
    "surname": "Kowalska",
    "dateOfBirth": "1997-08-13",
    "email": "kkowalska@example.pl",
    "phoneNumber": "12345678",
    "reservations": [
        {
            "id": 1,
            "guestSurname": "Kowalska",
            "guestEmail": "kkowalska@example.pl",
            "reservedRoom": "18",
            "price": 1830.0,
            "paymentMethod": "CARD",
            "paymentStatus": "NOT_PAID",
            "startReservation": "2024-08-30",
            "endReservation": "2024-09-05"
        }
    ]
}
```

As the admin you can cancel any reservation as well:
```http request
http://localhost:8080/reservations/{reservationId}
```
Example:
```http request
http://localhost:8080/reservations/2
```
In the confirmation of deleting the reservation you get a statement:
```json
"You deleted reservation with id 2"
```

If you want to add room, hit the endpoint:
```http request
http://localhost:8080/rooms/
```
Example request:
```json
{
    "roomNumber":"31",
    "pricePerNight":190,
    "capacity":3,
    "available":true,
    "hasHairDryer":true,
    "hasSauna":true,
    "hasPrivateBathroom":true,
    "hasAirConditioning":true,
    "hasBalcony":true
}
```
Example response:
```json
{
    "id": 52,
    "roomNumber": "31",
    "pricePerNight": 190.0,
    "capacity": 3,
    "available": true,
    "hasHairDryer": true,
    "hasSauna": true,
    "hasPrivateBathroom": true,
    "hasAirConditioning": true,
    "hasBalcony": true
}
```
