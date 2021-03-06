package com.library.validator;

import com.library.domain.Borrow;
import com.library.domain.Copy;
import com.library.domain.archive.DeleteCopy;
import com.library.domain.enums.BorrowArchiveReason;
import com.library.domain.enums.DeleteCopyReason;
import com.library.exception.CopyNotFoundException;
import com.library.mapper.archive.ArchiveMapper;
import com.library.service.BorrowDbService;
import com.library.service.CopyDbService;
import com.library.service.archive.BorrowArchiveDbService;
import com.library.service.archive.DeleteCopyDbService;
import com.library.validator.archive.BorrowArchiveValidator;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CopyValidator {
    private final CopyDbService copyDbService;
    private final DeleteCopyDbService deleteCopyDbService;
    private final ArchiveMapper archiveMapper;
    private final BorrowArchiveValidator borrowArchiveValidator;

    @Transactional
    public void deleteCopy(Long copyId, String reason) throws CopyNotFoundException {
        Copy copy = copyDbService.getCopyById(copyId).orElseThrow(CopyNotFoundException::new);
        DeleteCopy deleteCopy = archiveMapper.mapToDeleteCopy(copy, LocalDate.now(), DeleteCopyReason.valueOf(reason));
        Hibernate.initialize(copy.getBorrows());
        borrowArchiveValidator.archiveAllBorrows(copy.getBorrows(), BorrowArchiveReason.DELETE_COPY);
        deleteCopyDbService.saveDeleteCopy(deleteCopy);
        copyDbService.deleteCopy(copyId);
    }
}
