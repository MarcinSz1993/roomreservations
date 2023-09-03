package com.example.roomreservations.mapper;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;

import java.util.List;
import java.util.function.Function;

public class GuestMapper {
    public GuestDto guestToGuestDto (Guest guest) {
        List<ReservationDto> reservationDtos = guest.getReservations().stream()
                .map(new Function<Reservation, ReservationDto>() {

                    @Override
                    public ReservationDto apply(Reservation reservation) {
                        return new ReservationDto(
                                reservation.getId(),
                                reservation.getPrice(),
                                reservation.getPaymentMethod(),
                                reservation.getPaymentStatus(),
                                reservation.getStartReservation(),
                                reservation.getEndReservation());

                    }
                })
                .toList();
        return new GuestDto(
                guest.getId(),
                guest.getName(),
                guest.getSurname(),
                guest.getDateOfBirth(),
                guest.getEmail(),
                guest.getPhoneNumber(),
                reservationDtos

        );
    }
}
