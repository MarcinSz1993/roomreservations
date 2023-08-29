package com.example.roomreservations.exception;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String surname) {
        super("There is no guest with surname: " + surname);
    }
}
