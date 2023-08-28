package com.example.roomreservations.service;

import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room addRoom(Room room) {
       return roomRepository.save(room);
    }
}
