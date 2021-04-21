package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.Borrow;
import com.library.domain.Reader;
import com.library.domain.dto.BorrowDto;
import com.library.exception.BookCopyNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.service.BookCopyDBService;
import com.library.service.ReaderDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BorrowMapper {
    private final ReaderDBService readerDBService;
    private final BookCopyDBService bookCopyDBService;

    public Borrow mapToBorrow(final BorrowDto borrowDto) throws ReaderNotFoundException, BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyDBService.getBookCopy(borrowDto.getCopyId()).orElseThrow(BookCopyNotFoundException::new);
        Reader reader = readerDBService.getReader(borrowDto.getReaderId()).orElseThrow(ReaderNotFoundException::new);
        return new Borrow(borrowDto.getBorrowId(),
                bookCopy,
                reader,
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate());
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(borrow.getBorrowId(),
                borrow.getBookCopy().getCopyId(),
                borrow.getReaderId().getReaderId(),
                borrow.getBorrowDate(),
                borrow.getReturnDate());
    }

    public List<BorrowDto> mapToBorrowDtoList(final List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
