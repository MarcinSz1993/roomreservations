package com.example.roomreservations.controller;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    @GetMapping("/all")
    public List<Reservation> showAllReservations(){
        return reservationService.showAllReservations();
    }

    @PostMapping("/new")
    public Reservation createNewReservation(@RequestBody Reservation reservation) throws Throwable {
        return reservationService.createReservation(reservation);
    }
    @DeleteMapping("{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId){
        reservationService.deleteReservation(reservationId);
    }

}
