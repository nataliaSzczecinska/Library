package com.library.domain;

import com.library.domain.enums.Role;
import com.library.repetitory.ReaderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReaderTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    private char[] password1 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '1'};
    private char[] password2 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'};
    private char[] password3 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '3'};

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

    @AfterEach
    public void cleanUpDatabase() {
        readerRepository.deleteAll();
    }

    @Test
    public void saveReaderTest() {
        //Given
        List<Reader> readersBeforeSave = readerRepository.findAll();

        //When
        readerRepository.save(reader1);
        Long id = reader1.getId();
        List<Reader> readersAfterSave = readerRepository.findAll();
        Optional<Reader> readerOptional = readerRepository.findById(id);

        //Then
        assertEquals(0, readersBeforeSave.size());
        assertEquals(1, readersAfterSave.size());
        assertEquals(id, readersAfterSave.get(0).getId());
        assertEquals("Test name 1", readersAfterSave.get(0).getName());
        assertEquals("Test login 1", readersAfterSave.get(0).getLogin());
        assertEquals("test1@mail.com", readersAfterSave.get(0).getMailAddress());
        assertEquals(9, readersAfterSave.get(0).getPassword().length);
        assertEquals('1', readersAfterSave.get(0).getPassword()[8]);
        assertEquals(LocalDate.of(2001, 1, 1), readersAfterSave.get(0).getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readersAfterSave.get(0).getRole());
        assertTrue(readerOptional.isPresent());
    }

    @Test
    public void findReaderByIdTest() {
        //Given
        readerRepository.save(reader1);
        Long id = reader1.getId();;

        //When
        Optional<Reader> readerOptional = readerRepository.findById(id);

        //Then
        assertTrue(readerOptional.isPresent());
        assertEquals(id, readerOptional.get().getId());
        assertEquals("Test name 1", readerOptional.get().getName());
        assertEquals("Test login 1", readerOptional.get().getLogin());
        assertEquals("test1@mail.com", readerOptional.get().getMailAddress());
        assertEquals(9, readerOptional.get().getPassword().length);
        assertEquals('1', readerOptional.get().getPassword()[8]);
        assertEquals(LocalDate.of(2001, 1, 1), readerOptional.get().getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readerOptional.get().getRole());
    }

    @Test
    public void findAllReadersTest() {
        //Given
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        //When
        List<Reader> readers = readerRepository.findAll();

        //Then
        assertEquals(3, readers.size());
        assertEquals("Test name 1", readers.get(0).getName());
        assertEquals("Test login 1", readers.get(0).getLogin());
        assertEquals("test1@mail.com", readers.get(0).getMailAddress());
        assertEquals(9, readers.get(0).getPassword().length);
        assertEquals('1', readers.get(0).getPassword()[8]);
        assertEquals(LocalDate.of(2001, 1, 1), readers.get(0).getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readers.get(0).getRole());
        assertFalse(readers.get(1).isBlocked());
        assertEquals("Test name 2", readers.get(1).getName());
        assertEquals("Test login 2", readers.get(1).getLogin());
        assertEquals("test2@mail.com", readers.get(1).getMailAddress());
        assertEquals(9, readers.get(1).getPassword().length);
        assertEquals('2', readers.get(1).getPassword()[8]);
        assertEquals(LocalDate.of(2002, 2, 2), readers.get(1).getCreateAccountDate());
        assertEquals(Role.MANAGER, readers.get(1).getRole());
        assertFalse(readers.get(1).isBlocked());
        assertEquals("Test name 3", readers.get(2).getName());
        assertEquals("Test login 3", readers.get(2).getLogin());
        assertEquals("test3@mail.com", readers.get(2).getMailAddress());
        assertEquals(9, readers.get(2).getPassword().length);
        assertEquals('3', readers.get(2).getPassword()[8]);
        assertEquals(LocalDate.of(2003, 3, 3), readers.get(2).getCreateAccountDate());
        assertEquals(Role.ADMIN, readers.get(2).getRole());
        assertTrue(readers.get(2).isBlocked());
    }

    @Test
    public void deleteReaderTest() {
        //Given
        readerRepository.save(reader1);
        Long id = reader1.getId();
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        //When
        List<Reader> readersBeforeDelete = readerRepository.findAll();
        readerRepository.deleteById(id);
        List<Reader> readersAfterDelete = readerRepository.findAll();
        Optional<Reader> readerOptional = readerRepository.findById(id);

        //Then
        assertEquals(3, readersBeforeDelete.size());
        assertEquals(2, readersAfterDelete.size());
        assertEquals("Test name 1", readersBeforeDelete.get(0).getName());
        assertEquals("Test login 1", readersBeforeDelete.get(0).getLogin());
        assertEquals("test1@mail.com", readersBeforeDelete.get(0).getMailAddress());
        assertEquals(9, readersBeforeDelete.get(0).getPassword().length);
        assertEquals('1', readersBeforeDelete.get(0).getPassword()[8]);
        assertEquals(LocalDate.of(2001, 1, 1), readersBeforeDelete.get(0).getCreateAccountDate());
        assertEquals(Role.NORMAL_READER, readersBeforeDelete.get(0).getRole());
        assertFalse(readersBeforeDelete.get(1).isBlocked());
        assertEquals("Test name 2", readersBeforeDelete.get(1).getName());
        assertEquals("Test login 2", readersBeforeDelete.get(1).getLogin());
        assertEquals("test2@mail.com", readersBeforeDelete.get(1).getMailAddress());
        assertEquals(9, readersBeforeDelete.get(1).getPassword().length);
        assertEquals('2', readersBeforeDelete.get(1).getPassword()[8]);
        assertEquals(LocalDate.of(2002, 2, 2), readersBeforeDelete.get(1).getCreateAccountDate());
        assertEquals(Role.MANAGER, readersBeforeDelete.get(1).getRole());
        assertFalse(readersBeforeDelete.get(1).isBlocked());
        assertEquals("Test name 3", readersBeforeDelete.get(2).getName());
        assertEquals("Test login 3", readersBeforeDelete.get(2).getLogin());
        assertEquals("test3@mail.com", readersBeforeDelete.get(2).getMailAddress());
        assertEquals(9, readersBeforeDelete.get(2).getPassword().length);
        assertEquals('3', readersBeforeDelete.get(2).getPassword()[8]);
        assertEquals(LocalDate.of(2003, 3, 3), readersBeforeDelete.get(2).getCreateAccountDate());
        assertEquals(Role.ADMIN, readersBeforeDelete.get(2).getRole());
        assertTrue(readersBeforeDelete.get(2).isBlocked());
        assertFalse(readerOptional.isPresent());
        assertEquals("Test name 2", readersAfterDelete.get(0).getName());
        assertEquals("Test login 2", readersAfterDelete.get(0).getLogin());
        assertEquals("test2@mail.com", readersAfterDelete.get(0).getMailAddress());
        assertEquals(9, readersAfterDelete.get(0).getPassword().length);
        assertEquals('2', readersAfterDelete.get(0).getPassword()[8]);
        assertEquals(LocalDate.of(2002, 2, 2), readersAfterDelete.get(0).getCreateAccountDate());
        assertEquals(Role.MANAGER, readersAfterDelete.get(0).getRole());
        assertFalse(readersAfterDelete.get(0).isBlocked());
        assertEquals("Test name 3", readersAfterDelete.get(1).getName());
        assertEquals("Test login 3", readersAfterDelete.get(1).getLogin());
        assertEquals("test3@mail.com", readersAfterDelete.get(1).getMailAddress());
        assertEquals(9, readersAfterDelete.get(1).getPassword().length);
        assertEquals('3', readersAfterDelete.get(1).getPassword()[8]);
        assertEquals(LocalDate.of(2003, 3, 3), readersAfterDelete.get(1).getCreateAccountDate());
        assertEquals(Role.ADMIN, readersAfterDelete.get(1).getRole());
        assertTrue(readersAfterDelete.get(1).isBlocked());
    }
}
