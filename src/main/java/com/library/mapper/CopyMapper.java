package com.library.mapper;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.dto.CopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    public Copy mapToCopy(final CopyDto copyDto, final Title title) {
        return Copy.builder()
                .id(copyDto.getId())
                .title(title)
                .copyStatus(copyDto.getCopyStatus())
                .build();
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return CopyDto.builder()
                .id(copy.getId())
                .titleId(copy.getTitle().getId())
                .copyStatus(copy.getCopyStatus())
                .build();
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copies) {
        return copies.stream()
                .map(copy -> mapToCopyDto(copy))
                .collect(Collectors.toList());
    }
}
