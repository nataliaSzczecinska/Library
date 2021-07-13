package com.library.service;

import com.library.domain.Copy;
import com.library.repetitory.CopyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CopyDbService {
    private final CopyRepository copyRepository;

    public List<Copy> getAllCopies() {
        log.info("The all copies are going to be got from database");
        return copyRepository.findAll();
    }

    public Optional<Copy> getCopyById(Long copyId) {
        log.info("The copy with id {} is going to be found in database", copyId);
        return copyRepository.findById(copyId);
    }

    public Copy saveCopy(Copy copy) {
        log.info("The copy is going to be saved in database");
        return copyRepository.save(copy);
    }

    public void deleteCopy(Long copyId) {
        log.info("The copy with id {} is going to be deleted in database", copyId);
        copyRepository.deleteById(copyId);
    }
}
