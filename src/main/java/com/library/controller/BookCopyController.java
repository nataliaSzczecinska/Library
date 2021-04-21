package com.library.controller;

import com.library.domain.BookCopy;
import com.library.domain.dto.BookCopyDto;
import com.library.exception.BookCopyNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.BookCopyMapper;
import com.library.service.BookCopyDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/library/book_copy")
public class BookCopyController {

    private final BookCopyDBService bookCopyDBService;
    private final BookCopyMapper bookCopyMapper;

    @GetMapping(value = "getBookCopies")
    public List<BookCopyDto> getBookCopies() {
        List<BookCopy> bookCopyList = bookCopyDBService.getAllBookCopies();
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyList);
    }

    @GetMapping(value = "getBookCopy")
    public BookCopyDto getBookCopy(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyMapper
                .mapToBookCopyDto(bookCopyDBService
                        .getBookCopy(bookCopyId)
                        .orElseThrow(BookCopyNotFoundException::new));
    }

    @DeleteMapping(value = "deleteBookCopy")
    public void deleteBookCopy(@RequestParam Long bookCopyId) {
        bookCopyDBService.deleteBookCopyById(bookCopyId);
    }

    @PutMapping(value = "updateBookCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookCopyDto updateBookCopy(BookCopyDto bookCopyDto) throws TitleNotFoundException {
        BookCopy bookCopySave = bookCopyDBService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
        return bookCopyMapper.mapToBookCopyDto(bookCopySave);
    }

    @PostMapping(value = "createBookCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBookCopy(BookCopyDto bookCopyDto) throws TitleNotFoundException {
        bookCopyDBService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }
}
