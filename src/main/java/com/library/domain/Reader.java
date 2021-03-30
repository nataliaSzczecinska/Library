package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity(name = "READERS")
public class Reader {

    @Id
    @GeneratedValue
    @Column(name = "READER_ID")
    private Long readerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "CREATE_ACCOUNT_DATE")
    private LocalDate createAccountDate;
}
