package com.library.mapper;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.archive.DeleteCopy;
import com.library.domain.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto, final List<Copy> copies, final List<DeleteCopy> deleteCopies) {
        return Title.builder()
                .id(titleDto.getId())
                .title(titleDto.getTitle())
                .author(titleDto.getAuthor())
                .publisher(titleDto.getPublisher())
                .year(titleDto.getYear())
                .categories(titleDto.getCategories())
                .copies(copies)
                .deleteCopies(deleteCopies)
                .build();
    }

    public TitleDto mapToTitleDto(final Title title) {
        return TitleDto.builder()
                .id(title.getId())
                .title(title.getTitle())
                .author(title.getAuthor())
                .publisher(title.getPublisher())
                .year(title.getYear())
                .categories(title.getCategories())
                .build();
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titles) {
        return titles.stream()
                .map(title -> mapToTitleDto(title))
                .collect(Collectors.toList());
    }
}
