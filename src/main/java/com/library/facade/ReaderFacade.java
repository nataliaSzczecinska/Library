package com.library.facade;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.exception.ReaderLoginHasAlreadyExistException;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.ReaderPasswordRequirementsNotFulfilException;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderDbService;
import com.library.validator.ReaderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReaderFacade {
    private final ReaderMapper readerMapper;
    private final ReaderDbService readerDbService;
    private final ReaderValidator readerValidator;

    public List<ReaderDto> getAllReaders() {
        return readerValidator.getAllReadersWithCleanPassword();
    }

    public ReaderDto getReaderById(Long readerId) throws ReaderNotFoundException {
        ReaderDto readerDto = readerMapper.mapToReaderDto(readerDbService.getReaderById(readerId).orElseThrow(ReaderNotFoundException::new));
        readerDto.clearSensitive();
        return readerDto;
    }

    public void createReader(ReaderDto readerDto) throws ReaderPasswordRequirementsNotFulfilException, ReaderLoginHasAlreadyExistException {
        readerValidator.checkRequirementsToCreateReaderAccount(readerDto);
    }

    public ReaderDto updateReader(ReaderDto readerDto) throws ReaderNotFoundException {
        return readerValidator.checkRequirementsToUpdateReaderAccount(readerDto);
    }

    public ReaderDto blockedReader(Long readerId) throws ReaderNotFoundException {
        Reader reader = readerDbService.getReaderById(readerId).orElseThrow(ReaderNotFoundException::new);
        reader.setBlocked(true);
        Reader saveReader = readerDbService.saveReader(reader);
        reader.clearSensitive();
        saveReader.clearSensitive();
        return readerMapper.mapToReaderDto(saveReader);
    }

    public void deleteReader(Long readerId) throws ReaderNotFoundException {
        readerValidator.deleteReaderAccount(readerId);
    }
}
