package com.example.roomreservations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_number",unique = true)
    private String roomNumber;

    @Column(name = "price_per_night")
    private double pricePerNight;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "available")
    private boolean isAvailable;

    @Column(name = "facilities")
    private String facilities;

}



