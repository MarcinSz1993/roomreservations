package com.example.roomreservations.repository;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {

Optional<Guest> findBySurname(String surname);

}
