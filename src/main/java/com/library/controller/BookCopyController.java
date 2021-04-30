package com.library.controller;

import com.library.domain.*;
import com.library.domain.dto.BookCopyDto;
import com.library.domain.dto.BorrowDto;
import com.library.exception.BookCopyNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.BookCopyMapper;
import com.library.mapper.BorrowMapper;
import com.library.service.BookCopyDBService;
import com.library.service.BorrowDBService;
import com.library.service.ReaderDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/library/book_copy")
public class BookCopyController {

    private final BookCopyDBService bookCopyDBService;
    private final BorrowDBService borrowDBService;
    private final ReaderDBService readerDBService;
    private final BookCopyMapper bookCopyMapper;
    private final BorrowMapper borrowMapper;

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
    public BookCopyDto updateBookCopy(@RequestBody BookCopyDto bookCopyDto) throws TitleNotFoundException, BookCopyNotFoundException {
        BookCopy copy = bookCopyDBService
                .getBookCopy(bookCopyDto.getCopyId())
                .orElseThrow(BookCopyNotFoundException::new);
        Title title = copy.getTitle();
        bookCopyDto.setTitleId(title.getTitleId());
        BookCopy bookCopySave = bookCopyDBService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
        return bookCopyMapper.mapToBookCopyDto(bookCopySave);
    }

    @PutMapping(value = "changeStatus")
    public BookCopyDto changeStatusBookCopy(@RequestParam Long bookCopyId, @RequestParam Status status) throws BookCopyNotFoundException {
        BookCopy copy = bookCopyDBService
                .getBookCopy(bookCopyId)
                .orElseThrow(BookCopyNotFoundException::new);
        copy.setStatus(status);
        BookCopy bookCopySave = bookCopyDBService.saveBookCopy(copy);
        return bookCopyMapper.mapToBookCopyDto(bookCopySave);
    }

    @PostMapping(value = "createBookCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBookCopy(@RequestBody BookCopyDto bookCopyDto) throws TitleNotFoundException {
        bookCopyDBService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @PutMapping(value = "borrowBookCopy")
    public BookCopyDto borrowBookCopy(@RequestParam Long readerId,
                                    @RequestParam Long copyId) throws ReaderNotFoundException, BookCopyNotFoundException {
        Reader reader = readerDBService.getReader(readerId).orElseThrow(ReaderNotFoundException::new);
        BookCopy copy = bookCopyDBService.getBookCopy(copyId).orElseThrow(BookCopyNotFoundException::new);
        if (copy.getStatus().equals(Status.AVAILABLE)) {
            copy.setStatus(Status.BORROWED);
            BookCopy copySave = bookCopyDBService.saveBookCopy(copy);
            Borrow borrow = new Borrow(copySave, reader, LocalDate.now());
            reader.getBorrowList().add(borrow);
            Reader readerSave = readerDBService.saveReader(reader);
            borrow.setReaderId(readerSave);
            Borrow borrowSave = borrowDBService.saveBorrow(borrow);
            return bookCopyMapper.mapToBookCopyDto(copy);
        }
        return null;
    }
}
