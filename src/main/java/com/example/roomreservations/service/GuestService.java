package com.example.roomreservations.service;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.mapper.ReservationMapper;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;
    private final ReservationMapper reservationMapper;

    public Guest registerGuest(Guest guest) {
       return guestRepository.save(guest);
    }

    public GuestDto findBySurname(String surname){
        Optional<Guest> optionalGuest = guestRepository.findBySurname(surname);
        if(optionalGuest.isPresent()){
            Guest guest = optionalGuest.get();
            List<ReservationDto> reservationDtos = guest.getReservations().stream()
                .map(new Function<Reservation, ReservationDto>() {
                    @Override
                    public ReservationDto apply(Reservation reservation) {
                        return reservationMapper.reservationToReservationDto(reservation);
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
        } else {
        throw new GuestNotFoundException(surname);
        }
    }


}
