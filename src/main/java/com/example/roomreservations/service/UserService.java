package com.example.roomreservations.service;

import com.example.roomreservations.model.Users;
import com.example.roomreservations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Users findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Users addUser(Users users) {
        return userRepository.save(users);
    }
}
