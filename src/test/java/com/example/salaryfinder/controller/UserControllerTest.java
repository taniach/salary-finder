package com.example.salaryfinder.controller;

import com.example.salaryfinder.domain.CustomSortOrder;
import com.example.salaryfinder.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUsers_success() {
        ResponseEntity<?> response = userController.getUsers(
                300,
                1000,
                1,
                Optional.of(2L),
                CustomSortOrder.SALARY);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetUsers_runtimeException() {
        when(userService.getUserCount()).thenReturn(2L);
        when(userService.getUsers(eq(300), eq(1000), eq(1), eq(2), any()))
                .thenThrow(new RuntimeException());

        ResponseEntity<?> response = userController.getUsers(
                300,
                1000,
                1,
                Optional.empty(),
                CustomSortOrder.SALARY);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
