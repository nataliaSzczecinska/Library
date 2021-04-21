package com.library.controller;

import com.library.domain.Borrow;
import com.library.domain.dto.BorrowDto;
import com.library.exception.BookCopyNotFoundException;
import com.library.exception.BorrowNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.mapper.BorrowMapper;
import com.library.service.BorrowDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/library/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowDBService borrowDBService;
    private final BorrowMapper borrowMapper;

    @GetMapping(value = "getBorrows")
    public List<BorrowDto> getBorrows() {
        List<Borrow> borrowList = borrowDBService.getAllBorrows();
        return borrowMapper.mapToBorrowDtoList(borrowList);
    }

    @GetMapping(value = "getBorrow")
    public BorrowDto getBorrow(@RequestParam Long borrowId) throws BorrowNotFoundException {
        return borrowMapper
                .mapToBorrowDto(borrowDBService
                        .getBorrow(borrowId)
                        .orElseThrow(BorrowNotFoundException::new));
    }

    @DeleteMapping(value = "deleteBorrow")
    public void deleteBorrow(@RequestParam Long borrowId) {
        borrowDBService.deleteBorrowById(borrowId);
    }

    @PutMapping(value = "updateBorrow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowDto updateBorrow(@RequestBody BorrowDto borrowDto) throws ReaderNotFoundException, BookCopyNotFoundException {
        Borrow borrowSave = borrowDBService.saveBorrow(borrowMapper.mapToBorrow(borrowDto));
        return borrowMapper.mapToBorrowDto(borrowSave);
    }

    @PostMapping(value = "createBorrow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBorrow(@RequestBody BorrowDto borrowDto) throws ReaderNotFoundException, BookCopyNotFoundException {
        borrowDBService.saveBorrow(borrowMapper.mapToBorrow(borrowDto));
    }
}
