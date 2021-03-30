package com.library.controller;

import com.library.domain.BorrowDto;
import com.library.mapper.BorrowMapper;
import com.library.service.BorrowDBService;
import lombok.RequiredArgsConstructor;
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
        return null;
    }

    @GetMapping(value = "getBorrow")
    public BorrowDto getBorrow(Long borrowId) {
        return null;
    }

    @DeleteMapping(value = "deleteBorrow")
    public void deleteBorrow(Long borrowId) {
        //
    }

    @PutMapping(value = "updateBorrow")
    public BorrowDto updateBorrow(BorrowDto borrowDto) {
        return null;
    }

    @PostMapping(value = "createBorrow")
    public void createBorrow(BorrowDto borrowDto) {
        //
    }
}
