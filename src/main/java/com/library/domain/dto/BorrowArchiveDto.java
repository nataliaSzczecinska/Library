package com.library.domain.dto;

import com.library.domain.enums.BorrowArchiveReason;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowArchiveDto {
    private Long id;
    private Long previousId;
    private Long copyId;
    private Long readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate realReturnDate;
    private boolean isFinish;
    private BorrowArchiveReason borrowArchiveReason;
    private LocalDate deleteDate;
}
