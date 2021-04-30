package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table
@Entity(name = "BORROWS")
public class Borrow {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BORROW_ID")
    private Long borrowId;

    @JoinColumn(name = "COPY_ID")
    @OneToOne(targetEntity = BookCopy.class)
    private BookCopy bookCopy;

    @JoinColumn(name = "READER_ID")
    @ManyToOne(targetEntity = Reader.class)
    private Reader readerId;

    @Column(name = "BORROW_DATE")
    private LocalDate borrowDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    public Borrow (BookCopy bookCopy, Reader reader, LocalDate borrowDate, LocalDate returnDate) {
        this.bookCopy = bookCopy;
        this.readerId = reader;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Borrow (BookCopy bookCopy, Reader reader, LocalDate borrowDate) {
        this.bookCopy = bookCopy;
        this.readerId = reader;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusMonths(3);
    }
}
