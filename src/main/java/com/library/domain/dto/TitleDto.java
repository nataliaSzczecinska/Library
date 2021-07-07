package com.library.domain.dto;

import com.library.domain.Copy;
import com.library.domain.enums.BookCategory;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private List<BookCategory> categories;
}
