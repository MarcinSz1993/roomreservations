package com.example.roomreservations.controller;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/register")
    public Guest registerGuest(@RequestBody Guest guest){
        return guestService.registerGuest(guest);
    }

    @GetMapping("/surname")
    public GuestDto findBySurname(@RequestParam String surname) {

        return guestService.findBySurname(surname);
    }
}
