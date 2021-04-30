package com.library.controller;

import java.util.*;

import com.library.domain.BookCopy;
import com.library.domain.Status;
import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.TitleMapper;
import com.library.service.TitleDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/title")
@RequiredArgsConstructor
public class TitleController {

    private final TitleDBService titleDBService;
    private final TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTitles")
    public List<TitleDto> getTitles() {
        List<Title> titleList = titleDBService.getAllTitles();
        return titleMapper.mapToTitleDtoList(titleList);
    }

    @GetMapping(value = "getTitle")
    public TitleDto getTitle(@RequestParam Long titleId) throws TitleNotFoundException {
        return titleMapper
                .mapToTitleDto(titleDBService
                        .getTitle(titleId)
                        .orElseThrow(TitleNotFoundException::new));
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(@RequestParam Long titleId) {
        titleDBService.deleteTitleById(titleId);
    }

    @PutMapping(value = "updateTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto updateTitle(@RequestBody TitleDto titleDto) {
        Title saveTitle = titleDBService.saveTitle(titleMapper.mapToTitle(titleDto));
        return titleMapper.mapToTitleDto(saveTitle);
    }

    @PostMapping(value = "createTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitleDto titleDto) {
        titleDBService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @GetMapping(value = "numberOfCopies")
    public int numberOfAvailableCopiesWithTitle(@RequestParam Long titleId) throws TitleNotFoundException {
        Title title = titleDBService
                .getTitle(titleId)
                .orElseThrow(TitleNotFoundException::new);
        if (title.getBookCopyList() == null) {
            return 0;
        }
        int count = 0;
        for (BookCopy copy : title.getBookCopyList()) {
            if (copy.getStatus().equals(Status.AVAILABLE)) {
                count++;
            }
        }
        return count;
    }
}
