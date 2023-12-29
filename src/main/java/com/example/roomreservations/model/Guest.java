package com.example.roomreservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "guest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email_address")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnore
    private List<Reservation> reservations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (getId() != null ? !getId().equals(guest.getId()) : guest.getId() != null) return false;
        if (getName() != null ? !getName().equals(guest.getName()) : guest.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(guest.getSurname()) : guest.getSurname() != null) return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(guest.getDateOfBirth()) : guest.getDateOfBirth() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(guest.getEmail()) : guest.getEmail() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(guest.getPhoneNumber()) : guest.getPhoneNumber() != null)
            return false;
        return getReservations() != null ? getReservations().equals(guest.getReservations()) : guest.getReservations() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getReservations() != null ? getReservations().hashCode() : 0);
        return result;
    }
}
