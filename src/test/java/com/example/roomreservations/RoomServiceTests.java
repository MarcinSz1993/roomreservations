/*
package com.example.roomreservations;

import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.repository.RoomRepository;
import com.example.roomreservations.service.ReservationService;
import com.example.roomreservations.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTests {

    @Mock
    RoomRepository roomRepository;
    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    RoomService roomService;



    @Test
    public void addRoomTest(){

        Room roomToAdd = new Room(1L,"13",100,1,true, Collections.emptyList());

        when(roomRepository.save(Mockito.any(Room.class))).thenReturn(roomToAdd);

        Room addedRoom = roomService.addRoom(roomToAdd);

        verify(roomRepository, times(1)).save(Mockito.any(Room.class));

        assertEquals(roomToAdd, addedRoom);

    }
    @Test
    public void testOccupiedRooms() {
        LocalDateTime startDate = LocalDateTime.of(2026, 10, 2, 11, 0); // Data początkowa
        LocalDateTime endDate = LocalDateTime.of(2026, 10, 3, 12, 0);   // Data końcowa

        // Dodaj logi do testu, aby zrozumieć, co się dzieje
        System.out.println("Test start");
        System.out.println("startDate: " + startDate);
        System.out.println("endDate: " + endDate);

        Guest guest = new Guest(1L, "John", "Doe", LocalDate.of(1993, Month.JANUARY, 6), "john@example.com", "123456789", null);
        Room room1 = new Room(1L, "101", 100.0, 2, true, null);
        Room room2 = new Room(2L, "102", 150.0, 3, true, null);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, 100.0, "Credit Card", "Paid", LocalDateTime.of(2024, 10, 1, 11, 30), LocalDateTime.of(2024, 10, 2, 11, 30), guest, room1));
        reservations.add(new Reservation(2L, 150.0, "PayPal", "Paid", LocalDateTime.of(2024, 10, 4, 11, 30), LocalDateTime.of(2024, 10, 4, 11, 30), guest, room2));




        when(reservationRepository.findAllByEndReservationAfterAndStartReservationBefore(startDate, endDate))
                .thenReturn(reservations);


        List<Room> occupiedRooms = roomService.occupiedRooms(startDate, endDate);


        assertEquals(2, occupiedRooms.size());
        assertTrue(occupiedRooms.contains(room1));
        assertTrue(occupiedRooms.contains(room2));
    }

    @Test
    public void testOccupiedRoomsWithInvalidDates() {

        LocalDateTime startDate = LocalDateTime.of(2023, 9, 30, 12, 0); // Data początkowa
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 30, 10, 0);   // Data końcowa


        RoomService roomService = new RoomService(roomRepository, reservationRepository);


        //List<Room> occupiedRooms = roomService.occupiedRooms(startDate, endDate);

        assertThrows(WrongDatesException.class,() -> roomService.occupiedRooms(startDate,endDate));
    }
}
*/
