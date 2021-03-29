package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.BookCopyDto;
import java.util.*;
import java.util.stream.Collectors;

public class BookCopyMapper {
    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(bookCopyDto.getCopyId(),
                bookCopyDto.getTitleId(),
                bookCopyDto.getStatus());
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(bookCopy.getCopyId(),
                bookCopy.getTitleId(),
                bookCopy.getStatus());
    }

    public List<BookCopy> mapToBookCopyList(final List<BookCopyDto> bookCopyDtoList) {
        return bookCopyDtoList.stream()
                .map(this::mapToBookCopy)
                .collect(Collectors.toList());
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
