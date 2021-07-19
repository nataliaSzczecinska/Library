package com.library.mapper;

import com.library.domain.Borrow;
import com.library.domain.Copy;
import com.library.domain.Reader;
import com.library.domain.Title;
import com.library.domain.dto.ReaderDto;
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

public class ReaderMapperTestSuite {
    @Autowired
    private ReaderMapper readerMapper;

    private char[] password1 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '1'};
    private char[] password2 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'};
    private char[] password3 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '3'};

    private Reader reader1 = Reader.builder()
            .id(1L)
            .name("Test name 1")
            .login("Test login 1")
            .mailAddress("test1@mail.com")
            .password(password1)
            .createAccountDate(LocalDate.of(2001, 1, 1))
            .blocked(false)
            .role(Role.NORMAL_READER)
            .build();
    private Reader reader2 = Reader.builder()
            .id(2L)
            .name("Test name 2")
            .login("Test login 2")
            .mailAddress("test2@mail.com")
            .password(password2)
            .createAccountDate(LocalDate.of(2002, 2, 2))
            .blocked(false)
            .role(Role.MANAGER)
            .build();
    private Reader reader3 = Reader.builder()
            .id(3L)
            .name("Test name 3")
            .login("Test login 3")
            .mailAddress("test3@mail.com")
            .password(password3)
            .createAccountDate(LocalDate.of(2003, 3, 3))
            .blocked(true)
            .role(Role.ADMIN)
            .build();

    private ReaderDto reader1Dto = ReaderDto.builder()
            .id(1L)
            .name("Test name 1")
            .login("Test login 1")
            .mailAddress("test1@mail.com")
            .password(password1)
            .createAccountDate("2001-01-01")
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

    private Copy copy = Copy.builder()
            .id(1L)
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();

    private Borrow borrow = Borrow.builder()
            .id(1L)
            .copy(copy)
            .reader(reader1)
            .borrowDate(LocalDate.of(2011, 11, 11))
            .returnDate(LocalDate.of(2012, 2, 11))
            .realReturnDate(LocalDate.of(2012, 2, 8))
            .isFinish(true)
            .build();

    @Test
    public void mapToReaderTest() {
        //Given

        //When
        Reader reader = readerMapper.mapToReader(reader1Dto, Arrays.asList(borrow));

        //Then
        assertEquals(1L, reader.getId());
        assertEquals("Test name 1", reader.getName());
        assertEquals("Test login 1", reader.getLogin());
        assertEquals("test1@mail.com", reader.getMailAddress());
        assertEquals(9, reader.getPassword().length);
        assertEquals('1', reader.getPassword()[8]);
        assertEquals(LocalDate.of(2001, 1, 1), reader.getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, reader.getRole());
        assertEquals(1, reader.getBorrows().size());
        assertEquals(1L, reader.getBorrows().get(0).getId());
        assertFalse(reader.isBlocked());
    }

    @Test
    public void mapToReaderDtoTest() {
        //Given

        //When
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader1);

        //Then
        assertEquals(1L, readerDto.getId());
        assertEquals("Test name 1", readerDto.getName());
        assertEquals("Test login 1", readerDto.getLogin());
        assertEquals("test1@mail.com", readerDto.getMailAddress());
        assertEquals(9, readerDto.getPassword().length);
        assertEquals('1', readerDto.getPassword()[8]);
        assertEquals("2001-01-01", readerDto.getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readerDto.getRole());
        assertFalse(readerDto.isBlocked());
    }

    @Test
    public void mapToReaderDtoList() {
        //Given
        List<Reader> readers = Arrays.asList(reader1, reader2, reader3);

        //When
        List<ReaderDto> readerDtoList = readerMapper.mapToReaderDtoList(readers);

        //Then
        assertEquals(3, readerDtoList.size());
        assertEquals(1L, readerDtoList.get(0).getId());
        assertEquals("Test name 1", readerDtoList.get(0).getName());
        assertEquals("Test login 1", readerDtoList.get(0).getLogin());
        assertEquals("test1@mail.com", readerDtoList.get(0).getMailAddress());
        assertEquals(9, readerDtoList.get(0).getPassword().length);
        assertEquals('1', readerDtoList.get(0).getPassword()[8]);
        assertEquals("2001-01-01", readerDtoList.get(0).getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readerDtoList.get(0).getRole());
        assertFalse(readerDtoList.get(1).isBlocked());
        assertEquals(2L, readerDtoList.get(1).getId());
        assertEquals("Test name 2", readerDtoList.get(1).getName());
        assertEquals("Test login 2", readerDtoList.get(1).getLogin());
        assertEquals("test2@mail.com", readerDtoList.get(1).getMailAddress());
        assertEquals(9, readerDtoList.get(1).getPassword().length);
        assertEquals('2', readerDtoList.get(1).getPassword()[8]);
        assertEquals("2002-02-02", readerDtoList.get(1).getCreateAccountDate());
        assertEquals(Role.MANAGER, readerDtoList.get(1).getRole());
        assertFalse(readerDtoList.get(1).isBlocked());
        assertEquals(3L, readerDtoList.get(2).getId());
        assertEquals("Test name 3", readerDtoList.get(2).getName());
        assertEquals("Test login 3", readerDtoList.get(2).getLogin());
        assertEquals("test3@mail.com", readerDtoList.get(2).getMailAddress());
        assertEquals(9, readerDtoList.get(2).getPassword().length);
        assertEquals('3', readerDtoList.get(2).getPassword()[8]);
        assertEquals("2003-03-03", readerDtoList.get(2).getCreateAccountDate());
        assertEquals(Role.ADMIN, readerDtoList.get(2).getRole());
        assertTrue(readerDtoList.get(2).isBlocked());
    }
}