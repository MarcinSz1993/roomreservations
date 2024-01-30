package com.example.roomreservations.mapper;

import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationDto reservationToReservationDto (Reservation reservation){
        if(reservation == null){
            return new ReservationDto(
                    0L,
                    0.0,
                    null,
                    null,
                    null,
                    null);
        } else
    return new ReservationDto(
            reservation.getId(),
            reservation.getPrice(),
            reservation.getPaymentMethod(),
            reservation.getPaymentStatus(),
            reservation.getStartReservation(),
            reservation.getEndReservation());
    }
}
