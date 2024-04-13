package com.example.roomreservations.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class ReservationRequest {
    private Long guestId;
    private Long roomId;
    private LocalDate startReservation;
    private LocalDate endReservation;
    private String paymentMethod;
}
