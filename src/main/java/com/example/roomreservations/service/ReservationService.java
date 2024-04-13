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
import com.example.roomreservations.request.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public Reservation newReservation(ReservationRequest request) throws Throwable {
        Reservation reservation = new Reservation();
        Guest guest = getGuestFromRepo(request);
        Room room = getRoomFromRepo(request);
        validateReservationDates(room,request);
        long days = calculateReservationDuration(request);
        reservation.setPrice(days * room.getPricePerNight());
        reservation.setRoom(room);
        reservation.setGuest(guest);
        reservation.setPaymentMethod(request.getPaymentMethod());
        reservation.setPaymentStatus("W trakcie");
        reservation.setStartReservation(request.getStartReservation());
        reservation.setEndReservation(request.getEndReservation());
        if(request.getStartReservation().isAfter(request.getEndReservation())){
            throw new WrongDatesException();
        }
        return reservationRepository.save(reservation);
    }


    public boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        List<Reservation> incorrectReservation = reservationRepository
                .findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(
                        room, endDate, startDate);

        return incorrectReservation.isEmpty();
    }


    public long calculateReservationDuration(ReservationRequest request){
        return ChronoUnit.DAYS.between(request.getStartReservation(), request.getEndReservation());
    }


    public Room getRoomFromRepo(ReservationRequest request) throws Throwable {
        Long roomId = request.getRoomId();
        return roomRepository.findById(roomId).orElseThrow((Supplier<Throwable>) () -> new RoomException(roomId));
    }


    public Guest getGuestFromRepo(ReservationRequest request) throws Throwable {
        Long guestId = request.getGuestId();
        return guestRepository.findById(guestId).orElseThrow((Supplier<Throwable>) () -> new GuestNotFoundException(guestId));
    }

    public void validateReservationDates(Room room, ReservationRequest request){
        if (!isRoomAvailable(room, request.getStartReservation(), request.getEndReservation())) {
            throw new DatesNotAvailableException();
        } else if (request.getStartReservation().isAfter(request.getEndReservation())) {
            throw new WrongDatesException();
        }
    }
}
