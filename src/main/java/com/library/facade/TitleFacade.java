package com.library.facade;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.TitleMapper;
import com.library.service.TitleDbService;
import com.library.validator.TitleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class TitleFacade {
    private final TitleMapper titleMapper;
    private final TitleDbService titleDbService;
    private final TitleValidator titleValidator;

    public List<TitleDto> getAllBookTitles() {
        return titleMapper.mapToTitleDtoList(titleDbService.getAllTitles());
    }

    public TitleDto getBookTitleById(Long id) throws TitleNotFoundException {
        return titleMapper.mapToTitleDto(titleDbService.getTitleById(id).orElseThrow(TitleNotFoundException::new));
    }

    public List<TitleDto> getBooksByTitleFragment(String title) {
        return titleMapper.mapToTitleDtoList(titleDbService.searchBookTitlesByTitleFragment(title));
    }

    public List<TitleDto> getBooksByAuthor(String author) {
        return titleMapper.mapToTitleDtoList(titleDbService.searchBookTitlesByAuthor(author));
    }

    public List<TitleDto> getBooksByYear(int year) {
        return titleMapper.mapToTitleDtoList(titleDbService.searchBookTitlesByYear(year));
    }

    public List<TitleDto> getBooksByPublisher(String publisher) {
        return titleMapper.mapToTitleDtoList(titleDbService.searchBookTitlesByPublisher(publisher));
    }

    public List<TitleDto> getBooksByCategory(String category) {
        return titleMapper.mapToTitleDtoList(titleValidator
                .findBooksByCategory(titleDbService.getAllTitles(), category));
    }

    public void createBookTitle(TitleDto titleDto) {
        titleDbService.saveTitle(titleMapper.mapToTitle(titleDto, new ArrayList<>(), new ArrayList<>()));
    }

    public TitleDto updateBookTitle(TitleDto titleDto) throws TitleNotFoundException {
        Title title = titleDbService.getTitleById(titleDto.getId()).orElseThrow(TitleNotFoundException::new);
        return titleMapper.mapToTitleDto(titleDbService.saveTitle(titleMapper.mapToTitle(titleDto, title.getCopies(), title.getDeleteCopies())));
    }
}
