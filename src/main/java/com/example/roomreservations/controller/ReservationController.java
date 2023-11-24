package com.example.roomreservations.controller;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;
    @Cacheable(cacheNames = "AllReservations")
    @GetMapping("/")
    public List<Reservation> getReservations(){
        return reservationService.showAllReservations();
    }

    @CachePut(cacheNames = "AllReservations", key = "#result.id")
    @PostMapping("/")
    public Reservation createNewReservation(@RequestBody Reservation reservation) throws Throwable {
        return reservationService.createReservation(reservation);
    }
    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId){
        reservationService.deleteReservation(reservationId);
    }

}
