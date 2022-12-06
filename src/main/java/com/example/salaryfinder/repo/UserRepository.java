package com.example.salaryfinder.repo;

import com.example.salaryfinder.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.salary >= :min AND u.salary <= :max")
    Page<User> findAllUsersWithinLimit(@Param("min") double min, @Param("max") double max, Pageable pageable);

    Optional<User> findByNameIgnoreCase(String name);
}
