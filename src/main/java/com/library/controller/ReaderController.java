package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import java.util.*;

import com.library.exception.ReaderNotFoundException;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderDBService readerDBService;
    private final ReaderMapper readerMapper;

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        List<Reader> readerList = readerDBService.getAllReaders();
        return readerMapper.mapToReaderDtoList(readerList);
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(@RequestParam Long readerId) throws ReaderNotFoundException {
        return readerMapper
                .mapToReaderDto(readerDBService
                        .getReader(readerId)
                        .orElseThrow(ReaderNotFoundException::new));
    }

    @DeleteMapping(value = "deleteReader")
    public void deleteReader(@RequestParam Long readerId) {
        readerDBService.deleteReaderById(readerId);
    }

    @PutMapping(value = "updateReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) {
        Reader readerSave = readerDBService.saveReader(readerMapper.mapToReader(readerDto));
        return readerMapper.mapToReaderDto(readerSave);
    }

    @PostMapping(value = "createReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        readerDBService.saveReader(readerMapper.mapToReader(readerDto));
    }
}
