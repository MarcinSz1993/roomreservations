package com.example.roomreservations.controller;

import com.example.roomreservations.model.Guest;
import com.example.roomreservations.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/register")
    public Guest registerGuest(@RequestBody Guest guest){
        return guestService.registerGuest(guest);
    }
}
