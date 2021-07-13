package com.library.service;

import com.library.domain.Borrow;
import com.library.repetitory.BorrowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BorrowDbService {
    private final BorrowRepository borrowRepository;

    public List<Borrow> getAllBorrows() {
        log.info("The all borrows are going to be got from database");
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrowById(Long borrowId) {
        log.info("The borrow with id {} are going to be got from database", borrowId);
        return borrowRepository.findById(borrowId);
    }

    public Borrow saveBorrow(Borrow borrow) {
        log.info("The borrow is going to be saved in database");
        return borrowRepository.save(borrow);
    }

    public void deleteBorrow(Long borrowId) {
        log.info("The borrow with id {} is going to be deleted in database", borrowId);
        borrowRepository.deleteById(borrowId);
    }
}
