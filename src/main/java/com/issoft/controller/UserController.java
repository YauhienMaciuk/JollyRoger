package com.issoft.controller;

import com.issoft.dto.UserDto;
import com.issoft.entity.User;
import com.issoft.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = convertToUser(userDto);
        userService.createUser(user);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> receiveUsers() {
        return ResponseEntity.ok(userService.receiveUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> receiveUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.receiveUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        User user = convertToUser(userDto);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    private User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
