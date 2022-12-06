package com.example.salaryfinder.service;

import com.example.salaryfinder.domain.User;
import com.example.salaryfinder.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private List<User> initializeUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("John", 0.0));
        users.add(new User("Mary Posa", 4000.00));
        users.add(new User("Alice", -700.0));
        users.add(new User("Bob", 489.90));
        return users;
    }

    @Test
    public void testCreateOrUpdateUser_createNewUser() {
        userService.createOrUpdateUser("Bob", 3500.0);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argument.capture());
        assertEquals("Bob", argument.getValue().getName());
    }

    @Test
    public void testCreateOrUpdateUser_updateExistingUser() {
        Optional<User> existingUser = Optional.of(new User("Alice", 3000.0));
        when(userRepository.findByNameIgnoreCase(any()))
                .thenReturn(existingUser);

        userService.createOrUpdateUser("Alice", 2500.0);
        verify(userRepository, times(1)).save(existingUser.get());
    }

    @Test
    public void testUploadData() {
        userService.uploadData(initializeUsers());
        verify(userRepository, times(3)).save(any());
    }
}
