package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue
    @Column(name = "COPY_ID")
    private Long copyId;

    @Column(name = "TITLE_ID")
    private Long titleId;

    @Column(name = "STATUS")
    private String status;
}
