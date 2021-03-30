package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ReaderDto {
    private Long readerId;
    private String name;
    private String surname;
    private LocalDate createAccountDate;
}
