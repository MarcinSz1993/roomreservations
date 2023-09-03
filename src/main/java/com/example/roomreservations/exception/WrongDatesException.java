package com.example.roomreservations.exception;

public class WrongDatesException extends RuntimeException{
    public WrongDatesException() {
        super("The start date cannot be later than end date!");
    }
}
