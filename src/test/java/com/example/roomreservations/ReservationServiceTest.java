package com.example.roomreservations;

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

        LocalDate startDate = LocalDate.of(2023,1,23);
        LocalDate endDate = LocalDate.of(2023,2,28);
        ReservationRequest reservationRequest = new ReservationRequest(1L,750L,startDate,endDate,"Gotówka");

        long expectedDays = 36;
        long actualDays = reservationService.calculateReservationDuration(reservationRequest);

        Assertions.assertEquals(expectedDays,actualDays);
    }

    @Test
    public void getRoomFromRepoTestWhenRoomExists() throws Throwable {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);
        Room expectedRoom = new Room(1L,"10",200,2,true,"internet,tv");
        ReservationRequest reservationRequest = new ReservationRequest(1L,750L,startDate,endDate,"Gotówka");

        when(roomRepository.findById(reservationRequest.getRoomId())).thenReturn(Optional.of(expectedRoom));

        Room actualRoom = reservationService.getRoomFromRepo(reservationRequest);

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
        ReservationRequest reservationRequest = new ReservationRequest(1L,750L,startDate,endDate,"Gotówka");

        when(roomRepository.findById(reservationRequest.getRoomId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RoomException.class,() -> reservationService.getRoomFromRepo(reservationRequest));
    }
    @Test
    public void getGuestFromRepoTestWhenGuestExists() throws Throwable {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate endDate = LocalDate.of(2023,12,25);
        Guest expectedGuest = new Guest(1L,"Jan","Nowak",LocalDate.of(1980,1,1),"jan@nowak.pl","123456",Collections.emptyList());
        ReservationRequest reservationRequest = new ReservationRequest(1L,750L,startDate,endDate,"Gotówka");

        when(guestRepository.findById(reservationRequest.getGuestId())).thenReturn(Optional.of(expectedGuest));

        Guest actualGuest = reservationService.getGuestFromRepo(reservationRequest);

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
        ReservationRequest reservationRequest = new ReservationRequest(1L,750L,startDate,endDate,"Gotówka");


        when(guestRepository.findById(reservationRequest.getGuestId())).thenReturn(Optional.empty());

        Assertions.assertThrows(GuestNotFoundException.class, () -> reservationService.getGuestFromRepo(reservationRequest));
    }
    @Test
    public void validateReservationDatesTestWhenStartDateIsAfterEndDate(){
        Room room = new Room(1L,"10",200,2,true,"internet,tv");

        ReservationRequest reservationRequest = new ReservationRequest(1L,750L,LocalDate.of(2023,12,25),LocalDate.of(2023,12,20),"Gotówka");


        Assertions.assertThrows(WrongDatesException.class,()->reservationService.validateReservationDates(room,reservationRequest));


    }

}
