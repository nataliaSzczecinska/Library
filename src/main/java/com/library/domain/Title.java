package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Title {
    private Long titleId;
    private String author;
    private String title;
    private int year;
}
