package com.library.controller;

import com.library.domain.dto.TitleDto;
import com.library.exception.TitleNotFoundException;
import com.library.facade.TitleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/titles")
public class TitleController {
    private final TitleFacade titleFacade;

    @GetMapping
    public List<TitleDto> getAllBookTitles() {
        return titleFacade.getAllBookTitles();
    }

    @GetMapping(value = "/{id}")
    public TitleDto getBookTitleById(@PathVariable Long id) throws TitleNotFoundException {
        return titleFacade.getBookTitleById(id);
    }

    @GetMapping(value = "/title/{title}")
    public List<TitleDto> getBooksByTitleFragment(@PathVariable String title) {
        return titleFacade.getBooksByTitleFragment(title);
    }

    @GetMapping(value = "/author/{author}")
    public List<TitleDto> getBooksByAuthor(@PathVariable String author) {
        return titleFacade.getBooksByAuthor(author);
    }

    @GetMapping(value = "/year/{year}")
    public List<TitleDto> getBooksByYear(@PathVariable int year) {
        return titleFacade.getBooksByYear(year);
    }

    @GetMapping(value = "/publisher/{publisher}")
    public List<TitleDto> getBooksByPublisher(@PathVariable String publisher) {
        return titleFacade.getBooksByPublisher(publisher);
    }

    @GetMapping(value = "/category/{category}")
    public List<TitleDto> getBooksByCategory(@PathVariable String category) {
        return titleFacade.getBooksByCategory(category);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBookTitle(@RequestBody TitleDto titleDto) {
        titleFacade.createBookTitle(titleDto);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public TitleDto updateBookTitle(@RequestBody TitleDto titleDto) throws TitleNotFoundException {
        return titleFacade.updateBookTitle(titleDto);
    }
}
