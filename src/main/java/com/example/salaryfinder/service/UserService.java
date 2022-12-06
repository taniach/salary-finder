package com.example.salaryfinder.service;

import com.example.salaryfinder.domain.CustomSortOrder;
import com.example.salaryfinder.domain.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers(double min, double max, long offset, long limit, CustomSortOrder sort);
    public User createOrUpdateUser(String name, Double salary);
    public long getUserCount();
    public void uploadData(List<User> data);
}
