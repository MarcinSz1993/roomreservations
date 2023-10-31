package com.example.roomreservations;

import com.example.roomreservations.dto.GuestDto;
import com.example.roomreservations.exception.GuestNotFoundException;
import com.example.roomreservations.exception.WrongDatesException;
import com.example.roomreservations.mapper.ReservationMapper;
import com.example.roomreservations.model.Guest;
import com.example.roomreservations.repository.GuestRepository;
import com.example.roomreservations.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTests {


//    @Test
//    public void registerGuest_Test() {
//        GuestService guestService = mock(GuestService.class);
//        given(guestService.registerGuest(Mockito.any(Guest.class))).willReturn(new Guest(null,"Jan","Kowalski", LocalDate.of(2000,1,1),"jan@Kowalski.pl","1234",null));
//
//        Guest guest = guestService.registerGuest(new Guest());
//        assertNull(guest.getId());
//        assertEquals(guest.getName(),"Jan");
//        assertEquals(guest.getSurname(),"Kowalski");
//        assertEquals(guest.getDateOfBirth(),LocalDate.of(2000,1,1));
//        assertEquals(guest.getEmail(),"jan@Kowalski.pl");
//        assertEquals(guest.getPhoneNumber(),"1234");
//        assertNull(guest.getReservations());
//
//    }



    @Mock
    private GuestRepository guestRepository;

    @Mock
    private ReservationMapper reservationMapper;
    @InjectMocks
    private GuestService guestService;

    @BeforeEach
    public void setUp() {

        guestService = new GuestService(guestRepository,reservationMapper);
    }

    @Test
    public void testRegisterGuest() {

        Guest guestToRegister = new Guest(null,"Marcin","Szabała",LocalDate.of(1993,1,6),"marcin@wp.pl","1234",null); // Tutaj dodaj dowolne dane gościa


        Mockito.when(guestRepository.save(Mockito.any(Guest.class))).thenReturn(guestToRegister);


        Guest registeredGuest = guestService.registerGuest(guestToRegister);


        Mockito.verify(guestRepository, Mockito.times(1)).save(Mockito.any(Guest.class));


        assertEquals(guestToRegister, registeredGuest);
    }
    @Test
    public void findBySurnameTestWhenSurnameExistsInDatabase(){
        String surnameToFind = "Szabała";
        Guest guestToFind = new Guest(1L,"Marcin","Szabała",LocalDate.of(1993,1,6),"marcin@wp.pl","1234", Collections.emptyList());

        Mockito.when(guestRepository.findBySurname(surnameToFind)).thenReturn(Optional.of(guestToFind));

        GuestDto foundGuest = guestService.findBySurname(surnameToFind);

        Mockito.verify(guestRepository, Mockito.times(1)).findBySurname(surnameToFind);

        Assertions.assertEquals(guestToFind.getId(),foundGuest.getId());
        Assertions.assertEquals(guestToFind.getName(),foundGuest.getName());
        Assertions.assertEquals(guestToFind.getSurname(),foundGuest.getSurname());
        Assertions.assertEquals(guestToFind.getDateOfBirth(),foundGuest.getDateOfBirth());
        Assertions.assertEquals(guestToFind.getEmail(),foundGuest.getEmail());
        Assertions.assertEquals(guestToFind.getPhoneNumber(),foundGuest.getPhoneNumber());
        Assertions.assertEquals(guestToFind.getReservations(),foundGuest.getReservations());
    }

    @Test
    public void findBySurnameTestWhenSurnameNotExistsInDatabase(){
        String notExistingSurname = "Szabała";

        Mockito.when(guestRepository.findBySurname(notExistingSurname)).thenReturn(Optional.empty());

        Assertions.assertThrows(GuestNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                guestService.findBySurname(notExistingSurname);
            }
        });

        Mockito.verify(guestRepository, Mockito.times(1)).findBySurname(notExistingSurname);


    }
}
