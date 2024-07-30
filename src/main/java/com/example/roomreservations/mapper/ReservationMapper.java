package com.example.roomreservations.mapper;

import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.model.PaymentStatus;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.request.MakeReservationRequest;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public static ReservationDto mapReservationToReservationDto (Reservation reservation){
        return ReservationDto.builder()
                .id(reservation.getId())
                .price(reservation.getPrice())
                .paymentMethod(reservation.getPaymentMethod())
                .paymentStatus(reservation.getPaymentStatus())
                .startReservation(reservation.getStartReservation())
                .endReservation(reservation.getEndReservation())
                .build();
    }

    public static ReservationDto mapMakeReservationRequestToReservationDto(MakeReservationRequest makeReservationRequest){
        return ReservationDto.builder()
                .id(makeReservationRequest.getId())
                .price(0.0)
                .paymentMethod(makeReservationRequest.getPaymentMethod())
                .paymentStatus(PaymentStatus.NOT_PAID)
                .startReservation(makeReservationRequest.getStartReservation())
                .endReservation(makeReservationRequest.getEndReservation())
                .build();
    }
}
