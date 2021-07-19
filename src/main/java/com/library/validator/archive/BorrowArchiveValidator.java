package com.library.validator.archive;

import com.library.domain.Borrow;
import com.library.domain.enums.BorrowArchiveReason;
import com.library.mapper.archive.ArchiveMapper;
import com.library.service.BorrowDbService;
import com.library.service.archive.BorrowArchiveDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BorrowArchiveValidator {
    private final ArchiveMapper archiveMapper;
    private final BorrowArchiveDbService borrowArchiveDbService;
    private final BorrowDbService borrowDbService;

    public void archiveAllBorrows(List<Borrow> borrows, BorrowArchiveReason borrowArchiveReason) {
        archiveMapper.mapToBorrowArchiveList(borrows, LocalDate.now(), borrowArchiveReason).stream()
                .forEach(borrowArchive -> {
                    borrowArchiveDbService.saveBorrowArchive(borrowArchive);
                    borrowDbService.deleteBorrow(borrowArchive.getPreviousId());
                });
    }
}
