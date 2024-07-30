package com.example.roomreservations.repository;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(
            Room room, LocalDate endDate, LocalDate startDate);
    List<Reservation> findAllByStartReservationAndEndReservation(LocalDate startReservation, LocalDate endReservation);
}
