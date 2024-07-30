package com.example.roomreservations.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "reservation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDate startReservation;
    private LocalDate endReservation;

    @ManyToOne
    @JoinColumn (name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn (name = "room_id")
    private Room room;
}
