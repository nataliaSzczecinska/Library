package com.library.service;

import java.util.*;

import com.library.domain.BookCopy;
import com.library.repository.BookCopyRepository;
import org.springframework.stereotype.Service;

@Service
public class BookCopyDBService {
    private final BookCopyRepository bookCopyRepository;

    public BookCopyDBService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }
}
