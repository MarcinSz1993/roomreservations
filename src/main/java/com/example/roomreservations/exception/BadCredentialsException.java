package com.example.roomreservations.exception;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException() {
        super("Invalid username or password");
    }
}
