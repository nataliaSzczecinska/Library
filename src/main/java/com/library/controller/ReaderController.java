package com.library.controller;

import com.library.domain.ReaderDto;
import java.util.*;
import java.util.*;
import com.library.domain.TitleDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/reader")
public class ReaderController {

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        return null;
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(Long readerId) {
        return null;
    }

    @DeleteMapping(value = "deleteReader")
    public void deleteReader(Long readerId) {
        //
    }
}
