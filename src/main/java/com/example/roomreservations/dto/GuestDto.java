package com.example.roomreservations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Builder

public class GuestDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private List<ReservationDto> reservations;

    public GuestDto(Long id, String name, String surname, LocalDate dateOfBirth, String email, String phoneNumber, List<ReservationDto> reservations) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservations = reservations;
    }
}
