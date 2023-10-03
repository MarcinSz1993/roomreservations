package com.example.roomreservations.service;

import com.example.roomreservations.exception.DatesNotAvailableException;
import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> showAvailableRooms(LocalDateTime startDate, LocalDateTime endDate) {

        List<Room> rooms = occupiedRooms(startDate, endDate);

        return roomRepository.findAll()
                .stream()
                .filter(room -> !rooms.contains(room))
                .toList();
    }

    public List<Room> occupiedRooms(LocalDateTime startDate, LocalDateTime endDate) {
        if(startDate.isAfter(endDate)){
            throw new WrongDatesException();
        }
        List<Reservation> occupiedRooms = reservationRepository.findAllByEndReservationAfterAndStartReservationBefore(startDate, endDate);
        return occupiedRooms.stream()
                .map(Reservation::getRoom)
                .toList();

    }


}
