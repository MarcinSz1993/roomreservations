package com.example.roomreservations.request;

import com.example.roomreservations.model.PaymentMethod;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MakeReservationRequest {
    private Long id;
    private String roomNumber;
    private LocalDate startReservation;
    private LocalDate endReservation;
    private PaymentMethod paymentMethod;
}
