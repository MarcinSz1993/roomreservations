package com.example.roomreservations.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateGuestRequest {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String phoneNumber;
}
