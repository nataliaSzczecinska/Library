package com.library.service;

import java.util.*;
import com.library.domain.Borrow;
import com.library.repository.BorrowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BorrowDBService {
    private final BorrowRepository borrowRepository;

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrow(final Long borrowId) {
        return borrowRepository.findById(borrowId);
    }

    public Borrow saveBorrow(final Borrow borrow) {
        return borrowRepository.save(borrow);
    }
}
