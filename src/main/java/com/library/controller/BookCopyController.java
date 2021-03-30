package com.library.controller;

import com.library.domain.BookCopyDto;
import com.library.mapper.BookCopyMapper;
import com.library.service.BookCopyDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/library/book_copy")
public class BookCopyController {

    private final BookCopyDBService bookCopyDBService;
    private final BookCopyMapper bookCopyMapper;

    @GetMapping(value = "getBookCopies")
    public List<BookCopyDto> getBookCopies() {
        return null;
    }

    @GetMapping(value = "getBookCopy")
    public BookCopyDto getBookCopy(Long bookCopyId) {
        return null;
    }

    @DeleteMapping(value = "deleteBookCopy")
    public void deleteBookCopy(Long bookCopyId) {
        //
    }

    @PutMapping(value = "updateBookCopy")
    public BookCopyDto updateBookCopy(BookCopyDto bookCopyDto) {
        return null;
    }

    @PostMapping(value = "createBookCopy")
    public void createBookCopy(BookCopyDto bookCopyDto) {
        //
    }
}
