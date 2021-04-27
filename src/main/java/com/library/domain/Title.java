package com.library.domain;

import java.util.*;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table
@Entity(name = "TITLES")
public class Title {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TITLE_ID", unique = true)
    private Long titleId;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YEAR")
    private int year;

    @OneToMany(
            mappedBy = "title",
            targetEntity = BookCopy.class,
            fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopyList;

    public Title(Long titleId, String author, String title, int year) {
        this.titleId = titleId;
        this.author = author;
        this.title = title;
        this.year = year;
    }
}
