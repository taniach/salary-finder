package com.example.salaryfinder.service;

import com.example.salaryfinder.domain.CustomSortOrder;
import com.example.salaryfinder.domain.User;
import com.example.salaryfinder.helper.OffsetLimitPageRequest;
import com.example.salaryfinder.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(double min, double max, long offset, long limit, CustomSortOrder sort) {
        Pageable pageable = new OffsetLimitPageRequest(offset, limit, sort);
        return userRepository.findAllUsersWithinLimit(min, max, pageable).getContent();
    }

    /**
     * If name doesn't exist, create a new user.
     * If the name already exists, update salary and return existing user
     * @param name Name of user
     * @param salary Salary of user
     * @return New or existing user
     */
    public User createOrUpdateUser(String name, Double salary) {
        Optional<User> user = userRepository.findByNameIgnoreCase(name);

        if (user.isPresent()) {
            User u = user.get();
            u.setSalary(salary);
            return userRepository.save(u);
        } else {
            return userRepository.save(new User(name, salary));
        }
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public void uploadData(List<User> data) {
        for (User user : data) {
            if (user.getSalary() >= 0.0) {
                createOrUpdateUser(user.getName().trim(), user.getSalary());
            }
        }
    }
}
