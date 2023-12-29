package com.example.roomreservations;

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
import com.example.roomreservations.service.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    GuestRepository guestRepository;

    @InjectMocks
    ReservationService reservationService;

    @Test
    public void isRoomAvailableTestWhenRoomIsAvailable(){

        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);

        Room room = new Room(1L,"10",200,2,true,"internet,tv");

        when(reservationRepository.findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(room,endDate,startDate))
                .thenReturn(Collections.emptyList());
        boolean isRoomAvailable = reservationService.isRoomAvailable(room,startDate,endDate);

        Assertions.assertTrue(isRoomAvailable);

    }

    @Test
    public void isRoomAvailableTestWhenRoomIsNotAvailable(){

        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);

        Room room = new Room(1L,"10",200,2,true,"internet,tv");
        Reservation reservation = new Reservation(1L,750,"Gotówka","Opłacono",startDate,endDate,null,room);

        when(reservationRepository.findByRoomAndStartReservationLessThanEqualAndEndReservationGreaterThanEqual(room,endDate,startDate))
                .thenReturn(List.of(reservation));
        boolean isRoomAvailable = reservationService.isRoomAvailable(room,startDate,endDate);

        Assertions.assertFalse(isRoomAvailable);
    }

    @Test
    public void calculateReservationDurationTest(){

        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);
        Reservation reservation = new Reservation(1L,750,"Gotówka","Opłacono",startDate,endDate,null,null);

        long expectedDays = 5;

        long actualDays = reservationService.calculateReservationDuration(reservation);

        Assertions.assertEquals(expectedDays,actualDays);
    }

    @Test
    public void getRoomFromRepoTestWhenRoomExists() throws Throwable {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);
        Room expectedRoom = new Room(1L,"10",200,2,true,"internet,tv");
        Reservation reservation = new Reservation(1L,750,"Gotówka","Opłacono",startDate,endDate,null,expectedRoom);

        when(roomRepository.findById(reservation.getId())).thenReturn(Optional.of(expectedRoom));

        Room actualRoom = reservationService.getRoomFromRepo(reservation);

        Assertions.assertEquals(expectedRoom.getId(),actualRoom.getId());
        Assertions.assertEquals(expectedRoom.getRoomNumber(),actualRoom.getRoomNumber());
        Assertions.assertEquals(expectedRoom.getPricePerNight(),actualRoom.getPricePerNight());
        Assertions.assertEquals(expectedRoom.getCapacity(),actualRoom.getCapacity());
        Assertions.assertEquals(expectedRoom.isAvailable(),actualRoom.isAvailable());
        Assertions.assertEquals(expectedRoom.getFacilities(),actualRoom.getFacilities());
    }

    @Test
    public void getRoomFromRepoTestWhenRoomNotExist() throws Throwable {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);
        Room expectedRoom = new Room(1L,"10",200,2,true,"internet,tv");
        Reservation reservation = new Reservation(1L,750,"Gotówka","Opłacono",startDate,endDate,null,expectedRoom);

        when(roomRepository.findById(reservation.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RoomException.class,() -> reservationService.getRoomFromRepo(reservation));
    }
    @Test
    public void getGuestFromRepoTestWhenGuestExists() throws Throwable {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);
        Guest expectedGuest = new Guest(1L,"Jan","Nowak",LocalDate.of(1980,1,1),"jan@nowak.pl","123456",Collections.emptyList());
        Reservation reservation = new Reservation(1L,750,"Gotówka","Opłacono",startDate,endDate,expectedGuest,null);

        when(guestRepository.findById(reservation.getId())).thenReturn(Optional.of(expectedGuest));

        Guest actualGuest = reservationService.getGuestFromRepo(reservation);

        Assertions.assertEquals(expectedGuest.getId(),actualGuest.getId());
        Assertions.assertEquals(expectedGuest.getName(),actualGuest.getName());
        Assertions.assertEquals(expectedGuest.getSurname(),actualGuest.getSurname());
        Assertions.assertEquals(expectedGuest.getDateOfBirth(),actualGuest.getDateOfBirth());
        Assertions.assertEquals(expectedGuest.getEmail(),actualGuest.getEmail());
        Assertions.assertEquals(expectedGuest.getPhoneNumber(),actualGuest.getPhoneNumber());
        Assertions.assertEquals(expectedGuest.getReservations(),actualGuest.getReservations());
    }

    @Test
    public void getGuestFromRepoTestWhenGuestNotExist() throws Throwable {
        LocalDate startDate = LocalDate.of(2023, 12, 20);
        LocalDate endDate = LocalDate.of(2023, 12, 25);
        Guest expectedGuest = new Guest(1L, "Jan", "Nowak", LocalDate.of(1980, 1, 1), "jan@nowak.pl", "123456", Collections.emptyList());
        Reservation reservation = new Reservation(1L, 750, "Gotówka", "Opłacono", startDate, endDate, expectedGuest, null);


        when(guestRepository.findById(reservation.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(GuestNotFoundException.class, () -> reservationService.getGuestFromRepo(reservation));
    }
    @Test
    public void validateReservationDatesTestWhenStartDateIsAfterEndDate(){
        Room room = new Room(1L,"10",200,2,true,"internet,tv");

        Reservation reservation = new Reservation(1L, 750, "Gotówka", "Opłacono", LocalDate.of(2023,12,25), LocalDate.of(2023,12,20), null, room);

        Assertions.assertThrows(WrongDatesException.class,()->reservationService.validateReservationDates(room,reservation));


    }

    /*@Test
    public void createReservationTest() throws Throwable {

        Room room = new Room(1L,"10",200,2,true,"internet,tv");

        Guest guest = new Guest(1L, "Jan", "Nowak", LocalDate.of(1980, 1, 1), "jan@nowak.pl", "123456", Collections.emptyList());

        Reservation reservation = new Reservation(1L, 1000, "Gotówka", "Opłacono", LocalDate.of(2023,12,20), LocalDate.of(2023,12,25), guest, room);

        when(reservationRepository.save(any(Reservation.class))).thenReturn(new Reservation());

        Reservation actualReservation = reservationService.createReservation(reservation);

        Assertions.assertEquals(reservation.getPrice(),actualReservation.getPrice());
    }*/


}
