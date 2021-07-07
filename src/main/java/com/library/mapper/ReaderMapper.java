package com.library.mapper;

import com.library.domain.Borrow;
import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto, final List<Borrow> borrows) {
        return Reader.builder()
                .id(readerDto.getId())
                .name(readerDto.getName())
                .login(readerDto.getLogin())
                .mailAddress(readerDto.getMailAddress())
                .password(readerDto.getPassword())
                .createAccountDate(readerDto.getCreateAccountDate())
                .blocked(readerDto.isBlocked())
                .role(readerDto.getRole())
                .borrows(borrows)
                .build();
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return ReaderDto.builder()
                .id(reader.getId())
                .name(reader.getName())
                .login(reader.getLogin())
                .mailAddress(reader.getMailAddress())
                .password(reader.getPassword())
                .createAccountDate(reader.getCreateAccountDate())
                .blocked(reader.isBlocked())
                .role(reader.getRole())
                .build();
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers) {
        return readers.stream()
                .map(reader -> mapToReaderDto(reader))
                .collect(Collectors.toList());
    }
}
