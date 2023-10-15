package com.example.roomreservations.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter

public class GuestDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    @JsonIgnore
    private String email;
    private String phoneNumber;
    private List<ReservationDto> reservations;

    public GuestDto(Long id, String name, String surname, LocalDateTime dateOfBirth, String email, String phoneNumber, List<ReservationDto> reservations) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservations = reservations;
    }
}
