package com.library.domain;

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
@Table(name = "BORROWS")
public class Borrow {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "BORROW_ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COPY_ID")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @NotNull
    @Column(name = "BORROW_DATE")
    private LocalDate borrowDate;

    @NotNull
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    @Column(name = "REAL_RETURN_DATE")
    private LocalDate realReturnDate;

    @NotNull
    @Column(name = "IS_BORROW_FINISH")
    private boolean isFinish;
}
