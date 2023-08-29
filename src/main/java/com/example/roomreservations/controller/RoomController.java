package com.example.roomreservations.controller;

import com.example.roomreservations.model.Room;
import com.example.roomreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/new")
    public Room addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }
    @GetMapping("/available")
    public List<Room> showAvailableRooms(){
        return roomService.showAvailableRooms();
    }
}
