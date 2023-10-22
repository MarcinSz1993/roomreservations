package com.example.roomreservations.service;

import com.example.roomreservations.exception.DatesNotAvailableException;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.exception.RoomException;
import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;



    public List<Reservation> showAllReservations(){
        return  reservationRepository.findAll();
    }

    public void deleteReservation(Long id){
        reservationRepository.deleteById(id);
    }



    public Reservation createReservation(Reservation reservation) throws Throwable {
        Guest guest = getGuestFromRepo(reservation);
        Room room = getRoomFromRepo(reservation);
        validateReservationDates(room,reservation);
        long days = calculateReservationDuration(reservation);
        reservation.setPrice(days * room.getPricePerNight());
        reservation.setRoom(room);
        reservation.setGuest(guest);
        return reservationRepository.save(reservation);
    }

    public boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        List<Reservation> incorrectReservation = reservationRepository
                .findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(
                        room, endDate, startDate);

        return incorrectReservation.isEmpty();
    }

    private long calculateReservationDuration(Reservation reservation){
        Period durationOfStaying = Period.between(reservation.getStartReservation(),reservation.getEndReservation());
        return durationOfStaying.getDays();
    }

    private Room getRoomFromRepo(Reservation reservation) throws Throwable {
        Long roomId = reservation.getRoom().getId();
        return roomRepository.findById(roomId).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RoomException(roomId);

            }
        });
    }

    private Guest getGuestFromRepo(Reservation reservation) throws Throwable {
        Long guestId = reservation.getGuest().getId();
        return guestRepository.findById(guestId).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new GuestNotFoundException(guestId);
            }
        });
    }

    private void validateReservationDates(Room room, Reservation reservation){
        if (!isRoomAvailable(room, reservation.getStartReservation(), reservation.getEndReservation())) {
            throw new DatesNotAvailableException();
        } else if (reservation.getStartReservation().isAfter(reservation.getEndReservation())) {
            throw new WrongDatesException();
        }
    }
}
