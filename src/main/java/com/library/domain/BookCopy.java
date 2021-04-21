package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Table
@Entity(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "COPY_ID", unique = true)
    private Long copyId;

    @JoinColumn(name = "TITLE_ID")
    @ManyToOne(targetEntity = Title.class)
    private Title title;

    @Column(name = "STATUS")
    private Status status;
}
