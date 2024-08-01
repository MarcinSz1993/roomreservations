package com.example.roomreservations.controller;

import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.request.MakeReservationRequest;
import com.example.roomreservations.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    @GetMapping("/all")
    public List<ReservationDto> showAllReservations(){
        return reservationService.showAllReservations();
    }

    @PostMapping("/")
    public ReservationDto createNewReservation(@RequestBody MakeReservationRequest makeReservationRequest,
                                               @CookieValue String token) {
        return reservationService.createReservation(makeReservationRequest,token);
    }
    @DeleteMapping("{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId){
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("You deleted reservation with id " + reservationId);
    }

}
