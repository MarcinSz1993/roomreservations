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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (Double.compare(room.getPricePerNight(), getPricePerNight()) != 0) return false;
        if (getCapacity() != room.getCapacity()) return false;
        if (isAvailable() != room.isAvailable()) return false;
        if (getId() != null ? !getId().equals(room.getId()) : room.getId() != null) return false;
        if (getRoomNumber() != null ? !getRoomNumber().equals(room.getRoomNumber()) : room.getRoomNumber() != null)
            return false;
        return getFacilities() != null ? getFacilities().equals(room.getFacilities()) : room.getFacilities() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRoomNumber() != null ? getRoomNumber().hashCode() : 0);
        temp = Double.doubleToLongBits(getPricePerNight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCapacity();
        result = 31 * result + (isAvailable() ? 1 : 0);
        result = 31 * result + (getFacilities() != null ? getFacilities().hashCode() : 0);
        return result;
    }
}



