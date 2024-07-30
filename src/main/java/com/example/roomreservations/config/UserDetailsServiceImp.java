package com.example.roomreservations.config;

import com.example.roomreservations.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final GuestRepository guestRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return guestRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + " not found"));
    }
}
