package com.library.service;

import java.util.*;
import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReaderDBService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReader(final Long readerId) {
        return readerRepository.findById(readerId);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }
}
