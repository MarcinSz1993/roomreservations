package com.example.roomreservations.controller;

import com.example.roomreservations.model.Room;
import com.example.roomreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/new")
    public Room addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }
}
