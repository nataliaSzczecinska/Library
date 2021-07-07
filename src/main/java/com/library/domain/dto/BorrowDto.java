package com.library.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowDto {
    private Long id;
    private Long copyId;
    private Long readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate realReturnDate;
    private boolean isFinish;
}
