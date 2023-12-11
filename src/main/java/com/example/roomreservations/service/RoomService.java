package com.example.roomreservations.service;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> showAvailableRooms(LocalDate startDate, LocalDate endDate){
        List<Room> mappedOccupiedRooms = getOccupiedRooms(startDate, endDate);
        return roomRepository.findAll().stream()
                .filter(room -> !mappedOccupiedRooms.contains(room))
                .toList();
    }
    public List<Room> showAvailableFilteredRooms(LocalDate startDate, LocalDate endDate,
                                                 int capacity,
                                                 List<String> facilities) {
        List<Room> rooms = showAvailableRooms(startDate, endDate);

        return rooms.stream()
                .filter(room -> room.getCapacity() == capacity)
                .filter(room -> {
                    String roomFacilities = room.getFacilities();
                    return roomFacilities != null && Arrays.asList(roomFacilities.split(",")).containsAll(facilities);
                })
                .toList();
    }

    private List<Room> getOccupiedRooms(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findAllByStartReservationAndEndReservation(startDate, endDate)
                .stream()
                .map(Reservation::getRoom)
          .toList();
    }
}
