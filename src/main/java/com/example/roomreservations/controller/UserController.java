package com.example.roomreservations.controller;

import com.example.roomreservations.model.Users;
import com.example.roomreservations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/")
    public Users createUser(@RequestBody Users users){
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        return userService.addUser(users);
    }

    @GetMapping("/byEmail")
    public Users findUserByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }
}
