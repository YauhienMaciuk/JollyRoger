package com.issoft.service;

import com.issoft.entity.User;

import java.util.List;

public interface UserService {

    List<User> receiveUsers();

    void createUser(User user);

    void deleteUser(Long id);

    User receiveUser(Long id);

    void updateUser(User user);
}
