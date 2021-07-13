package com.library.domain.archive;

import com.library.domain.enums.BorrowArchiveReason;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BORROW_ARCHIVE")
public class BorrowArchive {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "BORROW_ARCHIVE_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "PREVIOUS_COPY_ID", unique = true)
    private Long previousId;

    @NotNull
    @Column(name = "COPY_ID", unique = true)
    private Long copyId;

    @NotNull
    @Column(name = "READER_ID", unique = true)
    private Long readerId;

    @NotNull
    @Column(name ="BORROW_DATE")
    private LocalDate borrowDate;

    @NotNull
    @Column(name ="RETURN_DATE")
    private LocalDate returnDate;

    @Column(name ="REAL_RETURN_DATE")
    private LocalDate realReturnDate;

    @NotNull
    @Column(name ="IS_BORROW_FINISH")
    private boolean isFinish;

    @NotNull
    @Column(name = "BORROW_ARCHIVE_REASON")
    private BorrowArchiveReason borrowArchiveReason;

    @NotNull
    @Column(name = "DELETE_DATE")
    private LocalDate deleteDate;
}
