package com.issoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.issoft.common.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Birth_Day")
//    @JsonFormat(pattern = "yyyy::MM::dd'T'HH:mm:ss")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime birthDay;

    @Column(name = "Phone_Number")
    private int phoneNumber;
}
