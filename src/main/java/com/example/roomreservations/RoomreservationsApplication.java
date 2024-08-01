package com.example.roomreservations;

import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Role;
import com.example.roomreservations.repository.GuestRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootApplication
public class RoomreservationsApplication {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Guest admin = Guest.builder()
                .name("Marcin")
                .surname("Szabała")
                .dateOfBirth(LocalDate.of(1993,1,6))
                .email("marcin@example.pl")
                .password(passwordEncoder.encode("qwerty"))
                .phoneNumber("12343212")
                .role(Role.ROLE_ADMIN)
                .reservations(Collections.emptyList())
                .build();
        ConfigurableApplicationContext context = SpringApplication.run(RoomreservationsApplication.class, args);
        GuestRepository guestRepository = context.getBean(GuestRepository.class);
        guestRepository.save(admin);
    }
}
