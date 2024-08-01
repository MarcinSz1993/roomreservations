package com.example.roomreservations.dto;

import com.example.roomreservations.model.PaymentMethod;
import com.example.roomreservations.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private String guestSurname;
    private String guestEmail;
    private String reservedRoom;
    private double price;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDate startReservation;
    private LocalDate endReservation;
}
