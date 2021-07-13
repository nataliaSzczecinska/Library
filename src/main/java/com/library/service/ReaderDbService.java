package com.library.service;

import com.library.domain.Reader;
import com.library.repetitory.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderDbService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        log.info("The all readers are going to be got from database");
        return readerRepository.findAll();
    }

    public Optional<Reader> getReaderById(Long readerId) {
        log.info("The reader with id {} is going to be got from database", readerId);
        return readerRepository.findById(readerId);
    }

    public Reader saveReader(Reader reader) {
        log.info("The reader is going to be saved in database");
        return readerRepository.save(reader);
    }

    public void deleteById(Long readerId) {
        log.info("The reader with {} are going to be deleted from database", readerId);
        readerRepository.deleteById(readerId);
    }
}
