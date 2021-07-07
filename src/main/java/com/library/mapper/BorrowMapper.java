package com.library.mapper;

import com.library.domain.Borrow;
import com.library.domain.Copy;
import com.library.domain.Reader;
import com.library.domain.dto.BorrowDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowMapper {

    public Borrow mapToBorrow(final BorrowDto borrowDto, final Copy copy, final Reader reader) {
        return Borrow.builder()
                .id(borrowDto.getId())
                .copy(copy)
                .reader(reader)
                .borrowDate(borrowDto.getBorrowDate())
                .returnDate(borrowDto.getReturnDate())
                .realReturnDate(borrowDto.getRealReturnDate())
                .isFinish(borrowDto.isFinish())
                .build();
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return BorrowDto.builder()
                .id(borrow.getId())
                .copyId(borrow.getCopy().getId())
                .readerId(borrow.getReader().getId())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .realReturnDate(borrow.getRealReturnDate())
                .isFinish(borrow.isFinish())
                .build();
    }

    public List<BorrowDto> mapToBorrowDtoList(final List<Borrow> borrows) {
        return borrows.stream()
                .map(borrow -> mapToBorrowDto(borrow))
                .collect(Collectors.toList());
    }
}
