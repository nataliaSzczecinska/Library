package com.library.controller;

import java.util.*;

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
    public TitleDto getTitle(@RequestBody Long titleId) throws TitleNotFoundException {
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
    public void createTask(@RequestBody TitleDto titleDto) {
        titleDBService.saveTitle(titleMapper.mapToTitle(titleDto));
    }
}
