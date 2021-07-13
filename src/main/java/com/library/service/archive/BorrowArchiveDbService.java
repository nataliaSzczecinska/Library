package com.library.service.archive;

import com.library.domain.archive.BorrowArchive;
import com.library.domain.archive.DeleteCopy;
import com.library.repetitory.archive.BorrowArchiveRepository;
import com.library.repetitory.archive.DeleteCopyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BorrowArchiveDbService {
    private final BorrowArchiveRepository borrowArchiveRepository;

    public List<BorrowArchive> getAllBorrowArchive() {
        log.info("The all borrow from archive are going to be got from database");
        return borrowArchiveRepository.findAll();
    }

    public Optional<BorrowArchive> getBorrowArchiveById(Long borrowArchiveId) {
        log.info("The borrow from archive with {} is going to be got from database", borrowArchiveId);
        return borrowArchiveRepository.findById(borrowArchiveId);
    }

    public BorrowArchive saveBorrowArchive(BorrowArchive borrowArchive) {
        log.info("The borrow is going to be saved in archive database");
        return borrowArchiveRepository.save(borrowArchive);
    }
}
