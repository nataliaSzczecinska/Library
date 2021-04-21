package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.Title;
import com.library.domain.dto.BookCopyDto;
import com.library.exception.TitleNotFoundException;
import com.library.service.TitleDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookCopyMapper {
    @Autowired
    private final TitleDBService titleDBService;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) throws TitleNotFoundException {
        Title title = titleDBService.getTitle(bookCopyDto.getTitleId()).orElseThrow(TitleNotFoundException::new);
        return new BookCopy(bookCopyDto.getCopyId(),
                title,
                bookCopyDto.getStatus());
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(bookCopy.getCopyId(),
                bookCopy.getTitle().getTitleId(),
                bookCopy.getStatus());
    }

    public List<BookCopy> mapToBookCopyList(final List<BookCopyDto> bookCopyDtoList) throws TitleNotFoundException {
        List<BookCopy> list = new ArrayList<>();
        for (BookCopyDto copy : bookCopyDtoList) {
            list.add(mapToBookCopy(copy));
        }
        return list;
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
