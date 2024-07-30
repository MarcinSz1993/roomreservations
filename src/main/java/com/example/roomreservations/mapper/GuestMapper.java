package com.example.roomreservations.mapper;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Role;
import com.example.roomreservations.request.CreateGuestRequest;

import java.util.Collections;

public class GuestMapper {

    public static Guest mapCreateGuestRequestToGuest(CreateGuestRequest createGuestRequest) {
        return Guest.builder()
                .id(createGuestRequest.getId())
                .name(createGuestRequest.getName())
                .surname(createGuestRequest.getSurname())
                .dateOfBirth(createGuestRequest.getBirthDate())
                .email(createGuestRequest.getEmail())
                .password(createGuestRequest.getPassword())
                .phoneNumber(createGuestRequest.getPhoneNumber())
                .role(Role.USER)
                .reservations(Collections.emptyList())
                .build();
    }

    public static GuestDto mapGuestToGuestDto(Guest guest) {
        return GuestDto.builder()
                .id(guest.getId())
                .name(guest.getName())
                .surname(guest.getSurname())
                .dateOfBirth(guest.getDateOfBirth())
                .email(guest.getEmail())
                .phoneNumber(guest.getPhoneNumber())
                .reservations(Collections.emptyList())
                .build();
    }


}
