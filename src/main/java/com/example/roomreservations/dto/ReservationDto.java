package com.example.roomreservations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ReservationDto {


    private Long id;

    private double price;

    private String paymentMethod;

    private String paymentStatus;

    private LocalDate startReservation;

    private LocalDate endReservation;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationDto that = (ReservationDto) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getPaymentMethod().equals(that.getPaymentMethod())) return false;
        if (!getPaymentStatus().equals(that.getPaymentStatus())) return false;
        if (!getStartReservation().equals(that.getStartReservation())) return false;
        return getEndReservation().equals(that.getEndReservation());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId().hashCode();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getPaymentMethod().hashCode();
        result = 31 * result + getPaymentStatus().hashCode();
        result = 31 * result + getStartReservation().hashCode();
        result = 31 * result + getEndReservation().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", price=" + price +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", startReservation=" + startReservation +
                ", endReservation=" + endReservation +
                '}';
    }
}
