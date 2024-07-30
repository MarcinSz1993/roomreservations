package com.example.roomreservations.controller;

import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.request.MakeReservationRequest;
import com.example.roomreservations.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    @GetMapping("/")
    public List<Reservation> showAllReservations(){
        return reservationService.showAllReservations();
    }

    @PostMapping("/")
    public ReservationDto createNewReservation(@RequestBody MakeReservationRequest makeReservationRequest,
                                               @CookieValue String token) {
        return reservationService.createReservation(makeReservationRequest,token);
    }
    @DeleteMapping("{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId){
        reservationService.deleteReservation(reservationId);
    }

}
