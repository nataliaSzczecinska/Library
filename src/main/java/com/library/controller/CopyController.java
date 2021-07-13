package com.library.controller;

import com.library.domain.dto.CopyDto;
import com.library.exception.CopyNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.facade.CopyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library/copies")
public class CopyController {
    private final CopyFacade copyFacade;

    @GetMapping
    public List<CopyDto> getAllCopies() {
        return copyFacade.getAllCopies();
    }

    @GetMapping(value = "/{id}")
    public CopyDto getCopyById(@PathVariable Long id) throws CopyNotFoundException {
        return copyFacade.getCopyById(id);
    }

    @GetMapping(value = "/title/{titleId}")
    public List<CopyDto> getBooksByTitle(@PathVariable Long titleId) {
        return copyFacade.getBooksByTitle(titleId);
    }

    @GetMapping(value = "/title/{titleId}/available")
    public List<CopyDto> getAvailableCopiesByTitle(@PathVariable Long titleId) {
        return copyFacade.getAvailableBooksByTitle(titleId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopyDto copyDto) throws TitleNotFoundException {
        copyFacade.createCopy(copyDto);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public CopyDto updateBookTitle(@RequestBody CopyDto copyDto) throws TitleNotFoundException, CopyNotFoundException {
        return copyFacade.updateCopy(copyDto);
    }

    @DeleteMapping(value = "/{id}/{reason}")
    public void deleteCopy(@PathVariable Long id, @PathVariable String reason) throws CopyNotFoundException {
        copyFacade.deleteCopy(id, reason);
    }
}
