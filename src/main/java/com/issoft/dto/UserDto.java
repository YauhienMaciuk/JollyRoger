package com.issoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthDay;

    private int phoneNumber;
}
