package com.example.roomreservations.controller;

import com.example.roomreservations.model.Room;
import com.example.roomreservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/")
    public Room addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }
    @GetMapping("/available")
    public List<Room> showAvailableRooms(@RequestParam LocalDate startDate, LocalDate endDate){
        return roomService.showAvailableRooms(startDate,endDate);
    }
    @GetMapping("/filtered")
    public List<Room> showAvailableAndFilteredRooms(@RequestParam LocalDate startDate, LocalDate endDate,
                                                    @RequestParam(required = false) Integer capacity,
                                                    @RequestParam(required = false) Boolean hairDryer,
                                                    @RequestParam(required = false) Boolean sauna,
                                                    @RequestParam(required = false) Boolean privateBathroom,
                                                    @RequestParam(required = false) Boolean airConditioning,
                                                    @RequestParam(required = false) Boolean balcony){

        return roomService.showAvailableFilteredRooms(startDate, endDate,
                capacity,
                hairDryer,
                sauna,
                privateBathroom,
                airConditioning,
                balcony);
    }
}
