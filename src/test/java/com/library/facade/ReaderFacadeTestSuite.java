package com.library.facade;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.domain.enums.Role;
import com.library.exception.ReaderLoginHasAlreadyExistException;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.ReaderPasswordRequirementsNotFulfilException;
import com.library.repetitory.ReaderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReaderFacadeTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private ReaderFacade readerFacade;

    private char[] password1 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '1'};
    private char[] password2 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'};
    private char[] password3 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '3'};
    private char[] password1Dto = {'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '1', ';'};

    private Reader reader1 = Reader.builder()
            .name("Test name 1")
            .login("Test login 1")
            .mailAddress("test1@mail.com")
            .password(password1)
            .createAccountDate(LocalDate.of(2001, 1, 1))
            .blocked(false)
            .role(Role.NORMAL_READER)
            .build();
    private Reader reader2 = Reader.builder()
            .name("Test name 2")
            .login("Test login 2")
            .mailAddress("test2@mail.com")
            .password(password2)
            .createAccountDate(LocalDate.of(2002, 2, 2))
            .blocked(false)
            .role(Role.MANAGER)
            .build();
    private Reader reader3 = Reader.builder()
            .name("Test name 3")
            .login("Test login 3")
            .mailAddress("test3@mail.com")
            .password(password3)
            .createAccountDate(LocalDate.of(2003, 3, 3))
            .blocked(true)
            .role(Role.ADMIN)
            .build();

    private ReaderDto reader1Dto = ReaderDto.builder()
            .name("New reader")
            .login("New login")
            .mailAddress("new.test@mail.com")
            .password(password1Dto)
            .createAccountDate("2001-01-01")
            .blocked(false)
            .role(Role.NORMAL_READER)
            .build();


    @AfterEach
    public void cleanUp() {
        readerRepository.deleteAll();
    }

    @Test
    public void getAllReadersTest() {
        //Given
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        //When
        List<ReaderDto> readersDto = readerFacade.getAllReaders();

        //Then
        assertEquals(3, readersDto.size());
        assertEquals("Test name 1", readersDto.get(0).getName());
        assertEquals("Test login 1", readersDto.get(0).getLogin());
        assertEquals("test1@mail.com", readersDto.get(0).getMailAddress());
        assertEquals(9, readersDto.get(0).getPassword().length);
        assertNotEquals('1', readersDto.get(0).getPassword()[8]);
        assertEquals("2001-01-01", readersDto.get(0).getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readersDto.get(0).getRole());
        assertFalse(readersDto.get(1).isBlocked());
        assertEquals("Test name 2", readersDto.get(1).getName());
        assertEquals("Test login 2", readersDto.get(1).getLogin());
        assertEquals("test2@mail.com", readersDto.get(1).getMailAddress());
        assertEquals(9, readersDto.get(1).getPassword().length);
        assertNotEquals('2', readersDto.get(1).getPassword()[8]);
        assertEquals("2002-02-02", readersDto.get(1).getCreateAccountDate());
        assertEquals(Role.MANAGER, readersDto.get(1).getRole());
        assertFalse(readersDto.get(1).isBlocked());
        assertEquals("Test name 3", readersDto.get(2).getName());
        assertEquals("Test login 3", readersDto.get(2).getLogin());
        assertEquals("test3@mail.com", readersDto.get(2).getMailAddress());
        assertEquals(9, readersDto.get(2).getPassword().length);
        assertNotEquals('3', readersDto.get(2).getPassword()[8]);
        assertEquals("2003-03-03", readersDto.get(2).getCreateAccountDate());
        assertEquals(Role.ADMIN, readersDto.get(2).getRole());
        assertTrue(readersDto.get(2).isBlocked());
    }

    @Test
    public void getReaderByIdTest() throws ReaderNotFoundException {
        //Given
        readerRepository.save(reader1);
        Long id = reader1.getId();

        //When
        ReaderDto readerDto = readerFacade.getReaderById(id);

        //Then
        assertEquals(id, readerDto.getId());
        assertEquals("Test name 1", readerDto.getName());
        assertEquals("Test login 1", readerDto.getLogin());
        assertEquals("test1@mail.com", readerDto.getMailAddress());
        assertEquals(9, readerDto.getPassword().length);
        assertNotEquals('1', readerDto.getPassword()[8]);
        assertEquals("2001-01-01", readerDto.getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readerDto.getRole());
    }


    @Test
    public void createReaderTest() throws ReaderPasswordRequirementsNotFulfilException, ReaderLoginHasAlreadyExistException {
        //Given
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        //When
        List<ReaderDto> readersDtoBeforeCreate = readerFacade.getAllReaders();
        readerFacade.createReader(reader1Dto);
        List<ReaderDto> readersDtoAfterCreate = readerFacade.getAllReaders();

        //Then
        assertEquals(3, readersDtoBeforeCreate.size());
        assertEquals(4, readersDtoAfterCreate.size());
    }

    @Test
    public void updateReaderTest() throws ReaderNotFoundException {
        //Given
        readerRepository.save(reader1);
        Long id = reader1.getId();
        readerRepository.save(reader2);
        readerRepository.save(reader3);
        reader1Dto.setId(id);
        reader1Dto.setLogin(reader1.getLogin());

        //When
        List<ReaderDto> readersDtoBeforeCreate = readerFacade.getAllReaders();
        readerFacade.updateReader(reader1Dto);
        List<ReaderDto> readersDtoAfterCreate = readerFacade.getAllReaders();

        //Then
        assertEquals(3, readersDtoBeforeCreate.size());
        assertEquals(3, readersDtoAfterCreate.size());
        assertEquals(id, readersDtoBeforeCreate.get(0).getId());
        assertEquals(id, readersDtoAfterCreate.get(0).getId());
        assertEquals("Test name 1", readersDtoBeforeCreate.get(0).getName());
        assertEquals("New reader", readersDtoAfterCreate.get(0).getName());
    }

    @Test
    public void blockedReaderTest() throws ReaderNotFoundException {
        //Given
        readerRepository.save(reader1);
        Long id = reader1.getId();
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        //When
        ReaderDto readerDtoBeforeBlocked = readerFacade.getReaderById(id);
        readerFacade.blockedReader(id);
        ReaderDto readerDtoAfterBlocked = readerFacade.getReaderById(id);

        //Then
        assertEquals(id, readerDtoBeforeBlocked.getId());
        assertEquals(id, readerDtoAfterBlocked.getId());
        assertFalse(readerDtoBeforeBlocked.isBlocked());
        assertTrue(readerDtoAfterBlocked.isBlocked());
    }

    @Test
    public void deleteReaderTest() throws ReaderNotFoundException {
        //Given
        readerRepository.save(reader1);
        Long id = reader1.getId();
        readerRepository.save(reader2);
        readerRepository.save(reader3);
        reader1Dto.setId(id);
        reader1Dto.setLogin(reader1.getLogin());

        //When
        List<ReaderDto> readersDtoBeforeDelete = readerFacade.getAllReaders();
        readerFacade.deleteReader(id);
        List<ReaderDto> readersDtoAfterDelete = readerFacade.getAllReaders();

        //Then
        assertEquals(3, readersDtoBeforeDelete.size());
        assertEquals(2, readersDtoAfterDelete.size());
    }
}
