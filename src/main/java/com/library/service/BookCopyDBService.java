package com.library.service;

import java.util.*;

import com.library.domain.BookCopy;
import com.library.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCopyDBService {
    private final BookCopyRepository bookCopyRepository;

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public Optional<BookCopy> getBookCopy(final Long bookCopyId){
        return bookCopyRepository.findById(bookCopyId);
    }

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopyById(final Long bookCopyId) {
        bookCopyRepository.deleteById(bookCopyId);
    }
}
