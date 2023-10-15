package com.example.roomreservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "price_per_night")
    private double pricePerNight;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "available")
    private boolean isAvailable;
    @Column(name = "has_hair_dryer")
    private boolean hasHairDryer;
    @Column(name = "has_sauna")
    private boolean hasSauna;
    @Column(name = "has_private_bathroom")
    private boolean hasPrivateBathroom;
    @Column(name = "has_air_conditioning")
    private boolean hasAirConditioning;
    @Column(name = "has_balcony")
    private boolean hasBalcony;




}
