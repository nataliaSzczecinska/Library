package com.library.service;

import java.util.*;
import com.library.domain.Title;
import com.library.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TitleDBService {
    private final TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Optional<Title> getTitle(final Long titleId) {
        return titleRepository.findById(titleId);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitleById(final Long titleId) {
        titleRepository.deleteById(titleId);
    }
}
