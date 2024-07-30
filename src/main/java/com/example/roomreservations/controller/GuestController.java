package com.example.roomreservations.controller;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.model.AuthenticationResponse;
import com.example.roomreservations.request.CreateGuestRequest;
import com.example.roomreservations.request.LoginRequest;
import com.example.roomreservations.service.GuestService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guests")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/")
    public GuestDto registerGuest(@RequestBody CreateGuestRequest createGuestRequest){
        return guestService.registerGuest(createGuestRequest);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest,
                                        HttpServletResponse servletResponse){
        return guestService.login(loginRequest,servletResponse);
    }

    @GetMapping("/surname")
    public GuestDto findBySurname(@RequestParam String surname) {
        return guestService.findBySurname(surname);
    }
}
