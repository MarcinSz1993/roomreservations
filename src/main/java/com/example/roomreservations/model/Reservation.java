package com.example.roomreservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Guest guest;


    @ManyToOne
    @JoinColumn (name = "room_id")
    @JsonIgnore
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getPaymentMethod() != null ? !getPaymentMethod().equals(that.getPaymentMethod()) : that.getPaymentMethod() != null)
            return false;
        if (getPaymentStatus() != null ? !getPaymentStatus().equals(that.getPaymentStatus()) : that.getPaymentStatus() != null)
            return false;
        if (getStartReservation() != null ? !getStartReservation().equals(that.getStartReservation()) : that.getStartReservation() != null)
            return false;
        if (getEndReservation() != null ? !getEndReservation().equals(that.getEndReservation()) : that.getEndReservation() != null)
            return false;
        if (getGuest() != null ? !getGuest().equals(that.getGuest()) : that.getGuest() != null) return false;
        return getRoom() != null ? getRoom().equals(that.getRoom()) : that.getRoom() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getPaymentMethod() != null ? getPaymentMethod().hashCode() : 0);
        result = 31 * result + (getPaymentStatus() != null ? getPaymentStatus().hashCode() : 0);
        result = 31 * result + (getStartReservation() != null ? getStartReservation().hashCode() : 0);
        result = 31 * result + (getEndReservation() != null ? getEndReservation().hashCode() : 0);
        result = 31 * result + (getGuest() != null ? getGuest().hashCode() : 0);
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        return result;
    }
}
