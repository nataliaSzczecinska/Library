package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
    private Long borrowId;
    private Long copyId;
    private Long readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
