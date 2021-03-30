package com.library.controller;

import java.util.*;

import com.library.domain.Title;
import com.library.domain.TitleDto;
import com.library.mapper.TitleMapper;
import com.library.service.TitleDBService;
import lombok.RequiredArgsConstructor;
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
    public TitleDto getTitle(Long titleId) {
        return null;
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(Long titleId) {
        //
    }

    @PutMapping(value = "updateTitle")
    public TitleDto updateTitle(TitleDto titleDto) {
        return null;
    }

    @PostMapping(value = "createTitle")
    public void createTask(TitleDto titleDto) {

    }
}
