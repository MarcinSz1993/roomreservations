package com.example.roomreservations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "price_per_night")
    private double pricePerNight;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "Available")
    private boolean isAvailable;
}
