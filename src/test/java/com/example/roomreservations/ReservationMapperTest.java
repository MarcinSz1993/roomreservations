package com.example.roomreservations;

import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.mapper.ReservationMapper;
import com.example.roomreservations.model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ReservationMapperTest {

    @Test
    public void reservationToReservationDtoTest(){
        ReservationMapper reservationMapper = new ReservationMapper();

        Reservation reservationObject = new Reservation(1L,1000,"Gotówka","Opłacono", LocalDate.of(2023,5,12),LocalDate.of(2023,5,20),null,null);

        ReservationDto expectedMappingResult = new ReservationDto(
                                        reservationObject.getId(),
                                        reservationObject.getPrice(),
                                        reservationObject.getPaymentMethod(),
                                        reservationObject.getPaymentStatus(),
                                        reservationObject.getStartReservation(),
                                        reservationObject.getEndReservation());

        ReservationDto mappingResult = reservationMapper.reservationToReservationDto(reservationObject);

        Assertions.assertEquals(expectedMappingResult,mappingResult);
    }
    @Test
    public void reservationReservationDtoTestWhenReservationObjectIsNull(){
        ReservationMapper reservationMapper = new ReservationMapper();

        Reservation reservationObject = new Reservation(0L,0.0,null,null,null,null,null,null);

        ReservationDto expectedMappingResult = new ReservationDto(0L,0.0,null,null,null,null);

        ReservationDto mappingResult = reservationMapper.reservationToReservationDto(reservationObject);

        Assertions.assertEquals(expectedMappingResult.getId(),mappingResult.getId());
        Assertions.assertEquals(expectedMappingResult.getPrice(),mappingResult.getPrice());
        Assertions.assertEquals(expectedMappingResult.getPaymentMethod(),mappingResult.getPaymentMethod());
        Assertions.assertEquals(expectedMappingResult.getPaymentStatus(),mappingResult.getPaymentStatus());
        Assertions.assertEquals(expectedMappingResult.getStartReservation(),mappingResult.getStartReservation());
        Assertions.assertEquals(expectedMappingResult.getEndReservation(),mappingResult.getEndReservation());
    }
}
