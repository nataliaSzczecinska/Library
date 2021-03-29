package com.library.mapper;

import java.util.*;
import java.util.stream.Collectors;

import com.library.domain.Title;
import com.library.domain.TitleDto;

public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(titleDto.getTitleId(),
                titleDto.getAuthor(),
                titleDto.getTitle(),
                titleDto.getYear());
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(title.getTitleId(),
                title.getAuthor(),
                title.getTitle(),
                title.getYear());
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(this::mapToTitleDto)
                .collect(Collectors.toList());
    }

    public List<Title> mapToTitleList(final  List<TitleDto> titleDtoList) {
        return titleDtoList.stream()
                .map(this::mapToTitle)
                .collect(Collectors.toList());
    }
}
