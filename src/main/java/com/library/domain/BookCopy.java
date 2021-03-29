package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopy {
    private Long copyId;
    private Long titleId;
    private String status;
}
