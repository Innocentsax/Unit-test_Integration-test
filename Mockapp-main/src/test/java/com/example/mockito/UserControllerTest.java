package com.example.mockito;

import com.example.mockito.service.UserServiceImpl;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Test
    void getUserById() {
        Users user = Users.builder()
                .id(1L)
                .userIdentificationNo(UserServiceImpl.idGenerator(1L))
                .email("Egbon@adugbo.com")
                .gender("MF")
                .name("Egbon Abgero")
                .build();
        Mockito.when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<Users> users = userController.getUserById(1L);
        assertEquals(user, users.getBody());

    }

    @Test
    void createUser() {
        Users user = Users.builder()
                .id(1L)
                .userIdentificationNo(UserServiceImpl.idGenerator(1L))
                .email("Egbon@adugbo.com")
                .gender("MF")
                .name("Egbon Abgero")
                .build();
        Users userInputted = Users.builder()
                .id(null)
                .userIdentificationNo("")
                .email("Egbon@adugbo.com")
                .gender("MF")
                .name("Egbon Abgero")
                .build();
        Mockito.when(userService.createUser(userInputted)).thenReturn(user);
        ResponseEntity<Users> resultUser = userController.createUser(userInputted);
        assertEquals(user, resultUser.getBody());
    }

}