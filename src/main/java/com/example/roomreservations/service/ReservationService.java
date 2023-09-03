package com.example.roomreservations.service;

import com.example.roomreservations.exception.DatesNotAvailableException;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;



    public List<Reservation> showAllReservations(){
        return  reservationRepository.findAll();
    }

    public Reservation createNewReservation(Reservation reservation){

        Long guestId = reservation.getGuest().getId();
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException("Guest not found"));
        reservation.setGuest(guest);

        Duration durationOfStaying = Duration.between(reservation.getStartReservation(), reservation.getEndReservation());
        long days = durationOfStaying.toDays();

        Long roomId = reservation.getRoom().getId();
        Room room = roomRepository.findById(roomId).orElseThrow();
        reservation.setRoom(room);

        if (!isRoomAvailable(room, reservation.getStartReservation(), reservation.getEndReservation())) {
            throw new DatesNotAvailableException();
        } else if (reservation.getStartReservation().isAfter(reservation.getEndReservation())) {
            throw new WrongDatesException();
        }
        reservation.setPrice(days * room.getPricePerNight());
        room.setAvailable(false);

        return reservationRepository.save(reservation);
    }

    public boolean isRoomAvailable(Room room, LocalDateTime startDate, LocalDateTime endDate) {
        List<Reservation> conflictingReservations = reservationRepository
                .findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(
                        room, endDate, startDate);

        return conflictingReservations.isEmpty();
    }

}
