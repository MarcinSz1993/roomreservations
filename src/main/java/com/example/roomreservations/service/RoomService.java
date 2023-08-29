package com.example.roomreservations.service;

import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room addRoom(Room room) {
       return roomRepository.save(room);
    }

    public List<Room> showAvailableRooms(){
        return roomRepository.findAll().stream()
                .filter(new Predicate<Room>() {
                    @Override
                    public boolean test(Room room) {
                        return room.isAvailable();
                    }
                })
                .toList();
    }
}
