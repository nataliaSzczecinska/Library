package com.library.service;

import java.util.*;
import com.library.domain.Title;
import com.library.repository.TitleRepository;
import org.springframework.stereotype.Service;

@Service
public class TitleDBService {
    private final TitleRepository titleRepository;

    public TitleDBService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }
}
