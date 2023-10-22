package com.example.roomreservations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private double price;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDate startReservation;
    private LocalDate endReservation;

}
