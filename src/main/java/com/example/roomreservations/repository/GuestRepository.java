package com.example.roomreservations.repository;

import com.example.roomreservations.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {

}
