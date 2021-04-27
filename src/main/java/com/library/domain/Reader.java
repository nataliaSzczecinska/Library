package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Table
@Entity(name = "READERS")
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "READER_ID", unique = true)
    private Long readerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "CREATE_ACCOUNT_DATE")
    private LocalDate createAccountDate;

    @OneToMany(
            mappedBy = "readerId",
            targetEntity = Borrow.class,
            fetch = FetchType.LAZY)
    private List<Borrow> borrowList;

    public Reader(Long readerId, String name, String surname, LocalDate createAccountDate) {
        this.readerId = readerId;
        this.name = name;
        this.surname = surname;
        this.createAccountDate = createAccountDate;
    }
}
