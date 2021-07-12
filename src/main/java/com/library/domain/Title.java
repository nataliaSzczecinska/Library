package com.library.domain;

import com.library.domain.enums.BookCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Title.retrieveBookByTitleFragment",
                query = "FROM Title WHERE title LIKE: titleFragment"
        ),
        @NamedQuery(
                name = "Title.retrieveTitleByAuthor",
                query = "FROM Title WHERE author LIKE : author"
        ),
        @NamedQuery(
                name = "Title.retrieveTitleByPublisher",
                query = "FROM Title WHERE publisher LIKE : publisher"
        ),
        @NamedQuery(
                name = "Title.retrieveTitleByYear",
                query = "FROM Title WHERE year = : year"
        )
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TITLES")
public class Title {
    @Id
    @NotNull
    @GeneratedValue
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

    @ElementCollection(targetClass = BookCategory.class,
            fetch = FetchType.EAGER)
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
