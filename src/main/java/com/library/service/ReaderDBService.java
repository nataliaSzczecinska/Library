package com.library.service;

import java.util.*;
import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

@Service
public class ReaderDBService {
    private final ReaderRepository readerRepository;

    public ReaderDBService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
}
