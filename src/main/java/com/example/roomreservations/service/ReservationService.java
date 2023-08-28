package com.example.roomreservations.service;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public List<Reservation> showAllReservations(){
        return  reservationRepository.findAll();
    }
}
