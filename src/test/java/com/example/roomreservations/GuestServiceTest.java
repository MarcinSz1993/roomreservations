package com.example.roomreservations;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.dto.ReservationDto;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;
    @Test
    public void findBySurnameTestWhenSurnameExistsInDatabase() {
        Guest expectedGuest = new Guest(1L, "Jan", "Nowak", LocalDate.of(2000, 1, 30), "nowak@nowak.pl", "123456", Collections.emptyList());

        when(guestRepository.findBySurname("Nowak")).thenReturn(Optional.of(expectedGuest));

        Optional<Guest> actualGuestOptional = guestRepository.findBySurname("Nowak");

        assertTrue(actualGuestOptional.isPresent());

        Guest actualGuest = actualGuestOptional.get();

        assertEquals(expectedGuest, actualGuest);
    }

    @Test
    public void findBySurnameTestWhenSurnameNotExistsInDatabase() {
        GuestService guestService = Mockito.mock(GuestService.class);

        String surname = "Nowak";

        when(guestService.findBySurname(surname)).thenThrow(GuestNotFoundException.class);

        Assertions.assertThrows(GuestNotFoundException.class, () -> guestService.findBySurname(surname));
    }

    @Test
    public void findBySurnameTestWhenSurnameExistsButUserHasNoReservations(){
        GuestService guestService = Mockito.mock(GuestService.class);

        GuestDto expectedGuest = new GuestDto(1L, "Jan", "Nowak", LocalDate.of(2000, 1, 10), "jan@nowak.pl", "12345", Collections.emptyList());

        when(guestService.findBySurname("Nowak")).thenReturn(expectedGuest);

        GuestDto actualGuest = guestService.findBySurname("Nowak");

        Assertions.assertEquals(expectedGuest.getReservations(),actualGuest.getReservations());
    }
    @Test
    public void getReservationTestWhenGuestHasTwoReservations() {
        GuestService guestService = Mockito.mock(GuestService.class);

        Room room1 = new Room(1L, "10", 200, 2, true, "klimatyzacja,pralka");
        Room room2 = new Room(2L, "11", 250, 3, true, "wifi");

        Reservation reservation1 = new Reservation(1L, 1000, "Gotówka", "Opłacono", LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 15), null, room1);
        Reservation reservation2 = new Reservation(2L, 1500, "Karta", "Nie opłacono", LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 15), null, room2);

        Guest guest = new Guest(1L, "Jan", "Nowak", LocalDate.of(1993, 1, 5), "jan@nowak.pl", "1234", List.of(reservation1,reservation2));

        reservation1.setGuest(guest);
        reservation2.setGuest(guest);

        ReservationDto reservationDto1 = new ReservationDto(1L,1000,"Gotówka","Opłacono",LocalDate.of(2023,1,10),LocalDate.of(2023,1,15));
        ReservationDto reservationDto2 = new ReservationDto(2L,1500,"Karta","Nie opłacono",LocalDate.of(2023,10,10),LocalDate.of(2023,10,15));

        when(guestService.getReservationsDto(guest)).thenReturn(List.of(reservationDto1,reservationDto2));

        List<ReservationDto> reservationsDto = guestService.getReservationsDto(guest);

        Assertions.assertEquals(2,reservationsDto.size());
    }

    @Test
    public void getReservationTestWhenGuestHasNoReservations() {
        GuestService guestService = Mockito.mock(GuestService.class);

        Guest guest = new Guest(1L, "Jan", "Nowak", LocalDate.of(1993, 1, 5), "jan@nowak.pl", "1234", Collections.emptyList());

        when(guestService.getReservationsDto(guest)).thenReturn(Collections.emptyList());

        List<ReservationDto> reservationsDto = guestService.getReservationsDto(guest);

        Assertions.assertEquals(0,reservationsDto.size());
    }

    }


