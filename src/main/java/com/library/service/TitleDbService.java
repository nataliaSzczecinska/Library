package com.library.service;

import com.library.domain.Title;
import com.library.repetitory.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TitleDbService {
    private final TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Optional<Title> getTitleById(final Long titleId) {
        return titleRepository.findById(titleId);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitle(final Long titleId) {
        titleRepository.deleteById(titleId);
    }
}
