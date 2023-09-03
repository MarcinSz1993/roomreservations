package com.example.roomreservations.service;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public Room addRoom(Room room) {
       return roomRepository.save(room);
    }

//    public List<Room> showAvailableRooms(){
//        return roomRepository.findAll().stream()
//                .filter(new Predicate<Room>() {
//                    @Override
//                    public boolean test(Room room) {
//                        return room.isAvailable();
//                    }
//                })
//                .toList();
//    }

    public List<Room> showAvailableRooms(LocalDateTime startDate, LocalDateTime endDate){
        List<Reservation> foundReservations = reservationRepository.findAllByStartReservationAndEndReservation(startDate, endDate);
        return foundReservations.stream()
                .map(new Function<Reservation, Room>() {
                    @Override
                    public Room apply(Reservation reservation) {
                        return reservation.getRoom();
                    }
                })
                .toList();
        //dfhfghfhf

    };
}
