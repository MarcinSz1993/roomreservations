package com.example.roomreservations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private double price;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "start_reservation")
    private LocalDate startReservation;
    @Column(name = "end_reservation")
    private LocalDate endReservation;

    @ManyToOne
    @JoinColumn (name = "guest_id")
    private Guest guest;


    @ManyToOne
    @JoinColumn (name = "room_id")
    private Room room;

}
