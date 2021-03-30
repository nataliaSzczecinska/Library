package com.library.domain;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private Long copyId;
    private Long titleId;
    private String status;
}
