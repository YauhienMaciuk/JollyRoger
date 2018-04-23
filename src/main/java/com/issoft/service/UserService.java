package com.issoft.service;

import com.issoft.entity.User;

import java.util.Set;

public interface UserService {

    Set<User> receiveUsers();

    void createUser(User user);

    void deleteUser(Long id);

    User receiveUser(Long id);

    void updateUser(User user);
}
