package com.library.domain.dto;

import com.library.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookCopyDto {
    private Long copyId;
    private Long titleId;
    private Status status;

    public BookCopyDto(Long titleId, Status status) {
        this.titleId = titleId;
        this.status = status;
    }
}
