package com.library.domain.dto;

import com.library.domain.Status;
import com.library.domain.Title;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BookCopyDto {
    private Long copyId;
    private Long titleId;
    private Status status;
}
