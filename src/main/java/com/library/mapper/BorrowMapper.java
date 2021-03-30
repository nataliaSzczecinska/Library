package com.library.mapper;

import com.library.domain.Borrow;
import com.library.domain.BorrowDto;
import java.util.*;
import java.util.stream.Collectors;

public class BorrowMapper {
    public Borrow mapToBorrow(final BorrowDto borrowDto) {
        return new Borrow(borrowDto.getBorrowId(),
                borrowDto.getCopyId(),
                borrowDto.getReaderId(),
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate());
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(borrow.getBorrowId(),
                borrow.getCopyId(),
                borrow.getReaderId(),
                borrow.getBorrowDate(),
                borrow.getReturnDate());
    }

    public List<BorrowDto> mapToBorrowDtoList(final List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }

    public List<Borrow> mapToBorrowList(final List<BorrowDto> borrowDtoList) {
        return borrowDtoList.stream()
                .map(this::mapToBorrow)
                .collect(Collectors.toList());
    }
}
