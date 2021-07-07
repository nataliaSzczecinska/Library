package com.library.controller;

import com.library.domain.dto.ReaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/readers")
public class ReaderController {

    @GetMapping
    public List<ReaderDto> getAllReaders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{id}")
    public ReaderDto getReaderById(@PathVariable Long id) {
        return ReaderDto.builder().build();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        //
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) {
        return ReaderDto.builder().build();
    }

    @PutMapping(value = "/{id}/bocked")
    public ReaderDto blockedReader(@PathVariable Long id) {
        return ReaderDto.builder().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReader(@PathVariable Long id) {
        //
    }
}
