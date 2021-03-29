package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;

import java.util.*;
import java.util.stream.Collectors;

public class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(readerDto.getReaderId(),
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getCreateAccountDate());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(reader.getReaderId(),
                reader.getName(),
                reader.getSurname(),
                reader.getCreateAccountDate());
    }

    public List<Reader> mapToReaderList(final List<ReaderDto> readerDtoList) {
        return readerDtoList.stream()
                .map(this::mapToReader)
                .collect(Collectors.toList());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}
