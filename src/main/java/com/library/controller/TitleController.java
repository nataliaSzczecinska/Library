package com.library.controller;

import com.library.domain.dto.TitleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/titles")
public class TitleController {
    @GetMapping
    public List<TitleDto> getAllBookTitles() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{id}")
    public TitleDto getBookTitleById(@PathVariable Long id) {
        return TitleDto.builder().build();
    }

    @GetMapping(value = "/title/{title}")
    public List<TitleDto> getBooksByTitleFragment(@PathVariable String title) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/author/{author}")
    public List<TitleDto> getBooksByAuthor(@PathVariable String author) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/year/{year}")
    public List<TitleDto> getBooksByYear(@PathVariable int year) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/publisher/{publisher}")
    public List<TitleDto> getBooksByPublisher(@PathVariable String publisher) {
        return new ArrayList<>();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBookTitle(@RequestBody TitleDto titleDto) {
        //
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public TitleDto updateBookTitle(@RequestBody TitleDto titleDto) {
        return TitleDto.builder().build();
    }
}
