package com.example.roomreservations.service;

import com.example.roomreservations.model.Guest;
import com.example.roomreservations.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public Guest registerGuest(Guest guest) {
       return guestRepository.save(guest);
    }
}
