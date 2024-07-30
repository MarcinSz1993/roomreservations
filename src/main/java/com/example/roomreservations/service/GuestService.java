package com.example.roomreservations.service;

import com.example.roomreservations.config.JwtService;
import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.exception.BadCredentialsException;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.mapper.GuestMapper;
import com.example.roomreservations.mapper.ReservationMapper;
import com.example.roomreservations.model.AuthenticationResponse;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.request.CreateGuestRequest;
import com.example.roomreservations.request.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public GuestDto registerGuest(CreateGuestRequest createGuestRequest) {
        Guest guest = GuestMapper.mapCreateGuestRequestToGuest(createGuestRequest);
        String encodedPassword = passwordEncoder.encode(guest.getPassword());
        guest.setPassword(encodedPassword);
        guestRepository.save(guest);
        return GuestMapper.mapGuestToGuestDto(guest);
    }

    public AuthenticationResponse login(LoginRequest loginRequest,HttpServletResponse servletResponse) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException();
        }

        Guest guest = guestRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new GuestNotFoundException("Guest with email " + loginRequest.getEmail() + " not found"));

        String token = jwtService.generateToken(guest);
        addTokenToCookie(token,servletResponse);
        return new AuthenticationResponse(token);
    }

    public GuestDto findBySurname(String surname){
        Optional<Guest> optionalGuest = guestRepository.findBySurname(surname);
        if(optionalGuest.isPresent()){
            Guest guest = optionalGuest.get();
            List<Reservation> listOfReservationDto = guest.getReservations();
            GuestDto guestDto = GuestMapper.mapGuestToGuestDto(guest);
            guest.setReservations(listOfReservationDto);
            return guestDto;
        } else {
        throw new GuestNotFoundException(surname);
        }
    }

    private void addTokenToCookie(String token, HttpServletResponse servletResponse){
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(23*60*60);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
    }


}
