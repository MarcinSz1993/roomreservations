package com.example.roomreservations.service;

import com.example.roomreservations.config.JwtService;
import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.exception.DatesNotAvailableException;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.exception.RoomException;
import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.mapper.ReservationMapper;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import com.example.roomreservations.request.MakeReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final JwtService jwtService;

    public List<ReservationDto> showAllReservations(){
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations.stream()
                .map(ReservationMapper::mapReservationToReservationDto)
                .toList();
    }

    public void deleteReservation(Long id){
        reservationRepository.deleteById(id);
    }

    public ReservationDto createReservation(MakeReservationRequest makeReservationRequest,String token) {
        if(makeReservationRequest.getStartReservation().isAfter(makeReservationRequest.getEndReservation())){
            throw new WrongDatesException();
        }
        String guestEmail = jwtService.extractGuestEmail(token);
        Guest guest = guestRepository.findByEmail(guestEmail).orElseThrow(() -> new GuestNotFoundException(guestEmail));
        Room room = roomRepository.findByRoomNumber(makeReservationRequest.getRoomNumber()).orElseThrow(() -> new RoomException(makeReservationRequest.getId()));
        ReservationDto reservationDto = ReservationMapper.mapMakeReservationRequestToReservationDto(makeReservationRequest);

        validateReservationDates(makeReservationRequest.getRoomNumber(),makeReservationRequest.getStartReservation(),makeReservationRequest.getEndReservation());
        long days = calculateReservationDuration(makeReservationRequest.getStartReservation(),makeReservationRequest.getEndReservation());
        reservationDto.setPrice(days * room.getPricePerNight());
        reservationDto.setReservedRoom(makeReservationRequest.getRoomNumber());

        Reservation reservation = Reservation.builder()
                .price(reservationDto.getPrice())
                .paymentMethod(reservationDto.getPaymentMethod())
                .paymentStatus(reservationDto.getPaymentStatus())
                .startReservation(makeReservationRequest.getStartReservation())
                .endReservation(makeReservationRequest.getEndReservation())
                .guest(guest)
                .room(room)
                .build();
        reservationRepository.save(reservation);
        reservationDto.setId(reservation.getId());
        reservationDto.setGuestSurname(reservation.getGuest().getSurname());
        reservationDto.setGuestEmail(reservation.getGuest().getEmail());
        return reservationDto;
    }

    public boolean isRoomAvailable(String roomNumber, LocalDate startDate, LocalDate endDate) {
        Room room = roomRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new RoomException(Long.decode(roomNumber)));
        List<Reservation> incorrectReservation = reservationRepository
                .findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(
                        room, endDate, startDate);

        return incorrectReservation.isEmpty();
    }

    private long calculateReservationDuration(LocalDate startReservation, LocalDate endReservation){
        Period durationOfStaying = Period.between(startReservation,endReservation);
        return durationOfStaying.getDays();
    }

    private void validateReservationDates(String roomNumber, LocalDate startReservation, LocalDate endReservation){
        if (!isRoomAvailable(roomNumber, startReservation, endReservation)) {
            throw new DatesNotAvailableException();
        } else if (startReservation.isAfter(endReservation)) {
            throw new WrongDatesException();
        }
    }
}
