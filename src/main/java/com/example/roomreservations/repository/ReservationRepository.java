package com.example.roomreservations.repository;

import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(
            Room room, LocalDateTime endDate, LocalDateTime startDate);

    List<Reservation> findAllByStartReservationAndEndReservation(LocalDateTime startReservation, LocalDateTime endReservation);

}
