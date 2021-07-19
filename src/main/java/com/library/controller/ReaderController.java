package com.library.controller;

import com.library.domain.dto.ReaderDto;
import com.library.exception.ReaderLoginHasAlreadyExistException;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.ReaderPasswordRequirementsNotFulfilException;
import com.library.facade.ReaderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/readers")
public class ReaderController {
    private final ReaderFacade readerFacade;

    @GetMapping
    public List<ReaderDto> getAllReaders() {
        return readerFacade.getAllReaders();
    }

    @GetMapping(value = "/{id}")
    public ReaderDto getReaderById(@PathVariable Long id) throws ReaderNotFoundException {
        return readerFacade.getReaderById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) throws ReaderPasswordRequirementsNotFulfilException, ReaderLoginHasAlreadyExistException {
        readerFacade.createReader(readerDto);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) throws ReaderNotFoundException {
        return readerFacade.updateReader(readerDto);
    }

    @PutMapping(value = "/{id}/blocked")
    public ReaderDto blockedReader(@PathVariable Long id) throws ReaderNotFoundException {
        return readerFacade.blockedReader(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReader(@PathVariable Long id) throws ReaderNotFoundException {
        readerFacade.deleteReader(id);
    }
}
