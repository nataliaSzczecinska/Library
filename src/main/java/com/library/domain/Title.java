package com.library.domain;

import com.library.domain.enums.BookCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TITLES")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "TITLE_ID")
    private Long id;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "PUBLISHER")
    private String publisher;

    @NotNull
    @Column(name = "YEAR")
    private int year;

    @ElementCollection(targetClass = BookCategory.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="CATEGORIES")
    @Column(name = "CATEGORIES")
    private List<BookCategory> categories;

    @OneToMany(targetEntity = Copy.class,
            mappedBy = "title",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Copy> copies;
}
