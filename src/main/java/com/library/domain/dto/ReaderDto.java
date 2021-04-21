package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ReaderDto {
    private Long readerId;
    private String name;
    private String surname;
    private LocalDate createAccountDate;
}
