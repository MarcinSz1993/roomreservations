package com.example.roomreservations.service;

import com.example.roomreservations.exception.DatesNotAvailableException;
import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Room> showAvailableRooms(LocalDate startDate, LocalDate endDate){
        List<Room> mappedOccupiedRooms = getOccupiedRooms(startDate, endDate);
        return roomRepository.findAll().stream()
                .filter(new Predicate<Room>() {
                    @Override
                    public boolean test(Room room) {
                        return !mappedOccupiedRooms.contains(room);
                    }
                })
                .toList();
    }
    public List<Room> showAvailableFilteredRooms(LocalDate startDate, LocalDate endDate,
                                                 int capacity,
                                                 boolean hairDryer,
                                                 boolean sauna,
                                                 boolean privateBathroom,
                                                 boolean airConditioning,
                                                 boolean balcony){
        List<Room> rooms = showAvailableRooms(startDate, endDate);
               return rooms
                        .stream()
                        .filter(new Predicate<Room>() {
                    @Override
                    public boolean test(Room room) {
                        return room.getCapacity() == capacity &&
                                room.isHasHairDryer() == hairDryer &&
                                room.isHasSauna() == sauna &&
                                room.isHasPrivateBathroom() == privateBathroom &&
                                room.isHasAirConditioning() == airConditioning &&
                                room.isHasBalcony() == balcony;
                    }
                })
                .toList();
    }
  
    private List<Room> getOccupiedRooms(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findAllByStartReservationAndEndReservation(startDate, endDate)
                .stream()
                .map(new Function<Reservation, Room>() {
                    @Override
                    public Room apply(Reservation reservation) {
                        return reservation.getRoom();
                    }
                })
          .toList();
    }
}
