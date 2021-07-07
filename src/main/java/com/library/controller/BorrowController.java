package com.library.controller;

import com.library.domain.dto.BorrowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/borrows")
public class BorrowController {

    @GetMapping
    public List<BorrowDto> getAllBorrows() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{id}")
    public BorrowDto getBorrowById(@PathVariable Long id) {
        return BorrowDto.builder().build();
    }

    @GetMapping(value = "/reader/{readerId}")
    public List<BorrowDto> getBorrowsByReaderId(@PathVariable String readerId) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/title/{titleId}")
    public List<BorrowDto> getBorrowsByTitleId(@PathVariable String titleId) {
        return new ArrayList<>();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBorrow(@RequestBody BorrowDto borrowDto) {
        //
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public BorrowDto updateBorrow(@RequestBody BorrowDto borrowDto) {
        return BorrowDto.builder().build();
    }
}
