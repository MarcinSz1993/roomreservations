package com.example.roomreservations.exception;

public class DatesNotAvailableException extends  RuntimeException{
    public DatesNotAvailableException() {
        super("Room is not available for the specified dates");
    }
}
