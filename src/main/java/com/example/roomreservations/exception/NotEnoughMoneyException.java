package com.example.roomreservations.exception;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException() {
        super("You have not enough money to do this!");
    }
}
