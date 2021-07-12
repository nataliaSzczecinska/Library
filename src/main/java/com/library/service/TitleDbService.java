package com.library.service;

import com.library.domain.Title;
import com.library.facade.sql.SQLCondition;
import com.library.repetitory.TitleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class TitleDbService {
    private final TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        log.info("The all titles are going to be got from database");
        return titleRepository.findAll();
    }

    public Optional<Title> getTitleById(Long titleId) {
        log.info("The title with id {} is going to be found in database", titleId);
        return titleRepository.findById(titleId);
    }

    public Title saveTitle(Title title) {
        log.info("The title is going to be saved in database");
        return titleRepository.save(title);
    }

    public List<Title> searchBookTitlesByTitleFragment(String titleFragment) {
        log.info("The all titles with title fragment \"{}\" are going to be got from database", titleFragment);
        return titleRepository.retrieveBookByTitleFragment(SQLCondition.sqlCondition(titleFragment));
    }

    public List<Title> searchBookTitlesByAuthor(String author) {
        log.info("The all titles wrote by \"{}\" are going to be got from database", author);
        return titleRepository.retrieveTitleByAuthor(SQLCondition.sqlCondition(author));
    }

    public List<Title> searchBookTitlesByPublisher(String publisher) {
        log.info("The all titles published by \"{}\" are going to be got from database", publisher);
        return titleRepository.retrieveTitleByPublisher(SQLCondition.sqlCondition(publisher));
    }

    public List<Title> searchBookTitlesByYear(int year) {
        log.info("The all titles published in {} are going to be got from database", year);
        return titleRepository.retrieveTitleByYear(year);
    }
}
