package com.example.roomreservations.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String roomNumber;
    private double pricePerNight;
    private int capacity;
    private boolean available;
    private boolean hasHairDryer;
    private boolean hasSauna;
    private boolean hasPrivateBathroom;
    private boolean hasAirConditioning;
    private boolean hasBalcony;
}
