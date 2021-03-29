package com.library.service;

import java.util.*;
import com.library.domain.Borrow;
import com.library.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowDBService {
    private final BorrowRepository borrowRepository;

    public BorrowDBService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }
}
