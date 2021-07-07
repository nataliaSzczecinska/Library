package com.library.controller;

import com.library.domain.dto.CopyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/copies")
public class CopyController {

    @GetMapping
    public List<CopyDto> getAllCopies() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{id}")
    public CopyDto getCopyById(@PathVariable Long id) {
        return CopyDto.builder().build();
    }

    @GetMapping(value = "/title/{titleId}")
    public List<CopyDto> getBooksByTitle(@PathVariable Long titleId) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/title/{titleId}/available")
    public List<CopyDto> getAvailableCopiesByTitle(@PathVariable Long titleId) {
        return new ArrayList<>();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopyDto copyDto) {
        //
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public CopyDto updateBookTitle(@RequestBody CopyDto copyDto) {
        return CopyDto.builder().build();
    }
}
