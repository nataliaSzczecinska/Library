package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity(name = "BORROWS")
public class Borrow {

    @Id
    @GeneratedValue
    @Column(name = "BORROW_ID")
    private Long borrowId;

    @Column(name = "COPY_ID")
    private Long copyId;

    @Column(name = "READER_ID")
    private Long readerId;

    @Column(name = "BORROW_DATE")
    private LocalDate borrowDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
}
