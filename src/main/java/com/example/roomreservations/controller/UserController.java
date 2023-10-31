package com.example.roomreservations.controller;

import com.example.roomreservations.model.UserEntity;
import com.example.roomreservations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/new")
    public UserEntity createUser(@RequestBody UserEntity userEntity){
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        return userService.addUser(userEntity);
    }
    @GetMapping("/byEmail")
    public UserEntity findUserByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }
}
