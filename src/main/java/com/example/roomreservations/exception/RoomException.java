package com.example.roomreservations.exception;

public class RoomException extends RuntimeException{
    public RoomException(Long id) {
        super("The room with id:'" + id + "' doesn't exist!");
    }
}
