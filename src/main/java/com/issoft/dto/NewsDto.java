package com.issoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewsDto {

    private Long id;

    private Long authorId;

    private String title;

    private String Description;

    private String text;

    private LocalDateTime dateTime;
}
