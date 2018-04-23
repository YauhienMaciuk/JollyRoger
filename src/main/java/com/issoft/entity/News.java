package com.issoft.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "News")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Author_ID")
    private Long authorId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Text")
    private String text;

    @Column(name = "Date_Time")
    private LocalDateTime dateTime;
}
