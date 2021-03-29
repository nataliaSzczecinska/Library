package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Reader {
    private Long readerId;
    private String name;
    private String surname;
    private LocalDate createAccountDate;
}
