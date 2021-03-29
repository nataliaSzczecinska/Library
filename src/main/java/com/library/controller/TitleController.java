package com.library.controller;

import java.util.*;
import com.library.domain.TitleDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/title")
public class TitleController {

    @RequestMapping(method = RequestMethod.GET, value = "getTitles")
    public List<TitleDto> getTitles() {
        return null;
    }

    @GetMapping(value = "getTitle")
    public TitleDto getTitle(Long titleId) {
        return null;
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(Long titleId) {

    }

    @PutMapping(value = "updateTitle")
    public TitleDto updateTitle(TitleDto titleDto) {
        return null;
    }

    @PostMapping(value = "createTitle")
    public void createTask(TitleDto titleDto) {

    }
}
