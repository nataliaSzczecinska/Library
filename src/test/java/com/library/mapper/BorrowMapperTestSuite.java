package com.library.mapper;

import com.library.domain.Borrow;
import com.library.domain.Copy;
import com.library.domain.Reader;
import com.library.domain.Title;
import com.library.domain.dto.BorrowDto;
import com.library.domain.enums.BookCategory;
import com.library.domain.enums.CopyStatus;
import com.library.domain.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BorrowMapperTestSuite {
    @Autowired
    private BorrowMapper borrowMapper;

    private char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};

    private Reader reader = Reader.builder()
            .id(1L)
            .name("Test name 1")
            .login("Test login 1")
            .mailAddress("test1@mail.com")
            .password(password)
            .createAccountDate(LocalDate.of(2001, 1, 1))
            .blocked(false)
            .role(Role.NORMAL_READER)
            .build();

    private Title title = Title.builder()
            .id(1L)
            .title("Test title 1")
            .author("Test author 1")
            .publisher("Test publisher 1")
            .year(2001)
            .categories(Arrays.asList(BookCategory.ACTION, BookCategory.BIOGRAPHY))
            .build();

    private Copy copy1 = Copy.builder()
            .id(1L)
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy2 = Copy.builder()
            .id(2L)
            .title(title)
            .copyStatus(CopyStatus.BORROWED)
            .build();

    private Borrow borrow1 = Borrow.builder()
            .id(1L)
            .copy(copy1)
            .reader(reader)
            .borrowDate(LocalDate.of(2011, 11, 11))
            .returnDate(LocalDate.of(2012, 2, 11))
            .realReturnDate(LocalDate.of(2012, 2, 8))
            .isFinish(true)
            .build();
    private Borrow borrow2 = Borrow.builder()
            .id(2L)
            .copy(copy1)
            .reader(reader)
            .borrowDate(LocalDate.of(2015, 3, 14))
            .returnDate(LocalDate.of(2015, 6, 14))
            .realReturnDate(LocalDate.of(2015, 5, 21))
            .isFinish(true)
            .build();
    private Borrow borrow3 = Borrow.builder()
            .id(3L)
            .copy(copy2)
            .reader(reader)
            .borrowDate(LocalDate.of(2021, 7, 1))
            .returnDate(LocalDate.of(2021, 10, 1))
            .isFinish(false)
            .build();

    private BorrowDto borrow1Dto = BorrowDto.builder()
            .id(1L)
            .copyId(1L)
            .readerId(1L)
            .borrowDate(LocalDate.of(2011, 11, 11))
            .returnDate(LocalDate.of(2012, 2, 11))
            .realReturnDate(LocalDate.of(2012, 2, 8))
            .isFinish(true)
            .build();

    @Test
    public void mapToBorrowTest() {
        //Given

        //When
        Borrow borrow = borrowMapper.mapToBorrow(borrow1Dto, copy1, reader);

        //Then
        assertEquals(1L, borrow.getId());
        assertEquals(1L, borrow.getCopy().getId());
        assertEquals(1L, borrow.getCopy().getTitle().getId());
        assertEquals("Test title 1", borrow.getCopy().getTitle().getTitle());
        assertEquals(1L, borrow.getReader().getId());
        assertEquals("Test name 1", borrow.getReader().getName());
        assertEquals("Test login 1", borrow.getReader().getLogin());
        assertEquals(LocalDate.of(2011, 11, 11), borrow.getBorrowDate());
        assertEquals(LocalDate.of(2012, 2, 11), borrow.getReturnDate());
        assertEquals(LocalDate.of(2012, 2, 8), borrow.getRealReturnDate());
        assertTrue(borrow.isFinish());
    }

    @Test
    public void mapToBorrowDtoTest() {
        //Given

        //When
        BorrowDto borrowDto = borrowMapper.mapToBorrowDto(borrow1);

        //Then
        assertEquals(1L, borrowDto.getId());
        assertEquals(1L, borrowDto.getCopyId());
        assertEquals(1L, borrowDto.getReaderId());
        assertEquals(LocalDate.of(2011, 11, 11), borrowDto.getBorrowDate());
        assertEquals(LocalDate.of(2012, 2, 11), borrowDto.getReturnDate());
        assertEquals(LocalDate.of(2012, 2, 8), borrowDto.getRealReturnDate());
        assertTrue(borrowDto.isFinish());
    }

    @Test
    public void mapToBorrowDtoListTest() {
        //Given
        List<Borrow> borrows = Arrays.asList(borrow1, borrow2, borrow3);

        //When
        List<BorrowDto> borrowDtoList = borrowMapper.mapToBorrowDtoList(borrows);

        //Then
        assertEquals(3, borrowDtoList.size());
        assertEquals(1L, borrowDtoList.get(0).getId());
        assertEquals(1L, borrowDtoList.get(0).getCopyId());
        assertEquals(1L, borrowDtoList.get(0).getReaderId());
        assertEquals(LocalDate.of(2011, 11, 11), borrowDtoList.get(0).getBorrowDate());
        assertEquals(LocalDate.of(2012, 2, 11), borrowDtoList.get(0).getReturnDate());
        assertEquals(LocalDate.of(2012, 2, 8), borrowDtoList.get(0).getRealReturnDate());
        assertTrue(borrowDtoList.get(0).isFinish());
        assertEquals(2L, borrowDtoList.get(1).getId());
        assertEquals(1L, borrowDtoList.get(1).getCopyId());
        assertEquals(1L, borrowDtoList.get(1).getReaderId());
        assertEquals(LocalDate.of(2015, 3, 14), borrowDtoList.get(1).getBorrowDate());
        assertEquals(LocalDate.of(2015, 6, 14), borrowDtoList.get(1).getReturnDate());
        assertEquals(LocalDate.of(2015, 5, 21), borrowDtoList.get(1).getRealReturnDate());
        assertTrue(borrowDtoList.get(1).isFinish());
        assertEquals(3L, borrowDtoList.get(2).getId());
        assertEquals(2L, borrowDtoList.get(2).getCopyId());
        assertEquals(1L, borrowDtoList.get(2).getReaderId());
        assertEquals(LocalDate.of(2021, 7, 1), borrowDtoList.get(2).getBorrowDate());
        assertEquals(LocalDate.of(2021, 10, 1), borrowDtoList.get(2).getReturnDate());
        assertFalse(borrowDtoList.get(2).isFinish());
    }
}
