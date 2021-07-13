package com.library.mapper.archive;

import com.library.domain.Borrow;
import com.library.domain.Copy;
import com.library.domain.archive.BorrowArchive;
import com.library.domain.archive.DeleteCopy;
import com.library.domain.enums.BorrowArchiveReason;
import com.library.domain.enums.DeleteCopyReason;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArchiveMapper {
    public DeleteCopy mapToDeleteCopy(Copy copy, LocalDate deleteDate, DeleteCopyReason deleteCopyReason) {
        return DeleteCopy.builder()
                .previousId(copy.getId())
                .title(copy.getTitle())
                .deleteCopyReason(deleteCopyReason)
                .deleteDate(deleteDate)
                .build();
    }

    public BorrowArchive mapToBorrowArchive(Borrow borrow, LocalDate deleteDate, BorrowArchiveReason borrowArchiveReason) {
        return BorrowArchive.builder()
                .previousId(borrow.getId())
                .copyId(borrow.getCopy().getId())
                .readerId(borrow.getReader().getId())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .realReturnDate(borrow.getRealReturnDate())
                .isFinish(borrow.isFinish())
                .deleteDate(deleteDate)
                .borrowArchiveReason(borrowArchiveReason)
                .build();
    }

    public List<BorrowArchive> mapToBorrowArchiveList(List<Borrow> borrows, LocalDate deleteDate, BorrowArchiveReason borrowArchiveReason) {
        return borrows.stream()
                .map(borrow -> mapToBorrowArchive(borrow, deleteDate, borrowArchiveReason))
                .collect(Collectors.toList());
    }
}
