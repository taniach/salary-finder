package com.example.salaryfinder.repo;

import com.example.salaryfinder.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository.save(new User("John", 2500.02));
        userRepository.save(new User("Mary Posa", 4000.00));
        userRepository.save(new User("Alice", 3500.0));
        userRepository.save(new User("Bob", 489.90));
        userRepository.save(new User("Cathy", 100.0));
        userRepository.save(new User("David", 2800.00));
        userRepository.save(new User("Elaine", 3000.0));
    }

    @Test
    public void testFindAllUsersWithinLimit() {
        Assertions.assertEquals(5, userRepository
                .findAllUsersWithinLimit(2500, 4000, null)
                .getTotalElements());

        Assertions.assertEquals(2, userRepository
                .findAllUsersWithinLimit(0, 500, null)
                .getTotalElements());
    }

    @Test
    public void testFindByNameIgnoreCase() {
        Assertions.assertTrue(userRepository.findByNameIgnoreCase("ALICE").isPresent());
        Assertions.assertFalse(userRepository.findByNameIgnoreCase("Alice2").isPresent());
    }
}
