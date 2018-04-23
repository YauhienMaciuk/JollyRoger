package com.issoft.service;

import com.issoft.entity.User;
import com.issoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        user.setBirthDay(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public Set<User> receiveUsers() {
        Iterable<User> userIterable = userRepository.findAll();
        return StreamSupport.stream(userIterable.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User receiveUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user) {
        user.setBirthDay(LocalDateTime.now());
        userRepository.save(user);
    }
}
