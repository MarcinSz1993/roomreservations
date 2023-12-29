package com.example.roomreservations.service;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.mapper.ReservationMapper;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if(optionalGuest.isPresent())
        {
            Guest guest = optionalGuest.get();
            List<ReservationDto> reservationsDto = getReservationsDto(guest);
            return new GuestDto(
                guest.getId(),
                guest.getName(),
                guest.getSurname(),
                guest.getDateOfBirth(),
                guest.getEmail(),
                guest.getPhoneNumber(),
                reservationsDto);
        }
        else
        {
        throw new GuestNotFoundException(surname);
        }
    }

    public List<ReservationDto> getReservationsDto(Guest guest) {
        return guest.getReservations().stream()
            .map(reservationMapper::reservationToReservationDto)
            .toList();
    }


}
