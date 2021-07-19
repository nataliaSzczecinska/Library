package com.library.validator.requirement;

import com.library.domain.Reader;
import com.library.domain.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReaderRequirementsCheckerTestSuite {

    private char[] password1 = {'p', 'a', 's', 's', '1'};
    private char[] password2 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
    private char[] password3 = {'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '3'};
    private char[] password4 = {'P', 'a', 's', 's', 'w', 'o', 'r', 'd'};
    private char[] password5 = {'P', 'a', 's', 's', 'W', 'o', 'r', 'd', '4', '='};

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

    @Test
    public void checkCorrectLoginTest() {
        //Given
        List<Reader> readers = Arrays.asList(reader1, reader2, reader3);

        //When
        boolean expectTrue = ReaderRequirementsChecker.checkCorrectLogin("Test login 1", readers);
        boolean expectFalse = ReaderRequirementsChecker.checkCorrectLogin("New test login", readers);

        //Then
        assertTrue(expectTrue);
        assertFalse(expectFalse);
    }

    @Test
    public void checkCorrectPasswordTest() {
        //Given

        //When
        boolean expectFalse1 = ReaderRequirementsChecker.checkCorrectPassword(password1);
        boolean expectFalse2 = ReaderRequirementsChecker.checkCorrectPassword(password2);
        boolean expectFalse3 = ReaderRequirementsChecker.checkCorrectPassword(password3);
        boolean expectFalse4 = ReaderRequirementsChecker.checkCorrectPassword(password4);
        boolean expectTrue = ReaderRequirementsChecker.checkCorrectPassword(password5);

        //Then
        assertTrue(expectTrue);
        assertFalse(expectFalse1);
        assertFalse(expectFalse2);
        assertFalse(expectFalse3);
        assertFalse(expectFalse4);
    }
}
