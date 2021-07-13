package com.library.service.archive;

import com.library.domain.archive.DeleteCopy;
import com.library.repetitory.archive.DeleteCopyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteCopyDbService {
    private final DeleteCopyRepository deleteCopyRepository;

    public List<DeleteCopy> getAllDeleteCopies() {
        log.info("The all deleted copies are going to be got from database");
        return deleteCopyRepository.findAll();
    }

    public Optional<DeleteCopy> getDeleteCopyById(Long deleteCopyId) {
        log.info("The deleted copy with {} is going to be got from database", deleteCopyId);
        return deleteCopyRepository.findById(deleteCopyId);
    }

    public DeleteCopy saveDeleteCopy(DeleteCopy deleteCopy) {
        log.info("The deleted copy is going to be saved in database");
        return deleteCopyRepository.save(deleteCopy);
    }
}
