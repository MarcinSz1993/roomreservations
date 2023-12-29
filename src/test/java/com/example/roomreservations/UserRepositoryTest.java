package com.example.roomreservations;

import com.example.roomreservations.model.Users;
import com.example.roomreservations.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Test
    public void testFindByEmailWhenEmailNotExists() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        String nonExistingEmail = "nonexisting@example.com";

        Users expectedUser = new Users(1L, null, "qwerty", "ADMIN");

        when(userRepository.findByEmail(nonExistingEmail)).thenReturn(expectedUser);

        Users actualUser = userRepository.findByEmail("nonexisting@example.com");

        assertThat(actualUser.getEmail()).isNull();

    }

    @Test
    public void testFindByEmailWhenEmailExists() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        String existingEmail = "existing@example.com";

        Users expectedUser = new Users(1L, existingEmail, "qwerty", "ADMIN");

        when(userRepository.findByEmail(existingEmail)).thenReturn(expectedUser);

        Users actualUser = userRepository.findByEmail("existing@example.com");

        Assertions.assertEquals(expectedUser,actualUser);

    }
}
