package com.example.roomreservations.service;

import com.example.roomreservations.model.UserEntity;
import com.example.roomreservations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
