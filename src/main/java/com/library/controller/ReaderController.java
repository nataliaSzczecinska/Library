package com.library.controller;

import com.library.domain.ReaderDto;
import java.util.*;

import com.library.mapper.ReaderMapper;
import com.library.service.ReaderDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderDBService readerDBService;
    private final ReaderMapper readerMapper;

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        return null;
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(Long readerId) {
        return null;
    }

    @DeleteMapping(value = "deleteReader")
    public void deleteReader(Long readerId) {
        //
    }

    @PutMapping(value = "updateReader")
    public ReaderDto updateReader(ReaderDto readerDto) {
        return null;
    }

    @PostMapping(value = "createReader")
    public void createReader(ReaderDto readerDto) {
        //
    }
}
