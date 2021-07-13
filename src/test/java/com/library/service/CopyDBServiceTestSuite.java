package com.library.service;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.enums.BookCategory;
import com.library.domain.enums.CopyStatus;
import com.library.repetitory.CopyRepository;
import com.library.repetitory.TitleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CopyDBServiceTestSuite {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private CopyDbService copyDbService;

    private Title title = Title.builder()
            .title("Test title 1")
            .author("Test author 1")
            .publisher("Test publisher 1")
            .year(2001)
            .categories(Arrays.asList(BookCategory.ACTION, BookCategory.BIOGRAPHY))
            .build();

    private Copy copy1 = Copy.builder()
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy2 = Copy.builder()
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy3 = Copy.builder()
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();

    @BeforeEach
    public void saveTitleInDatabase() {
        titleRepository.save(title);
    }

    @AfterEach
    public void cleanUpDatabase() {
        copyRepository.deleteAll();
        titleRepository.deleteAll();
    }

    @Test
    public void getAllCopiesTest() {
        //Given
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<Copy> copies = copyDbService.getAllCopies();

        //Then
        assertEquals(3, copies.size());
        assertEquals("Test title 1", copies.get(0).getTitle().getTitle());
        assertEquals("Test title 1", copies.get(1).getTitle().getTitle());
        assertEquals("Test title 1", copies.get(2).getTitle().getTitle());
    }

    @Test
    public void getCopyByIdTest() {
        //Given
        copyRepository.save(copy1);
        Long id = copy1.getId();

        //When
        Optional<Copy> copyOptional = copyDbService.getCopyById(id);

        //Then
        assertTrue(copyOptional.isPresent());
        assertEquals(id, copyOptional.get().getId());
        assertEquals("Test title 1", copyOptional.get().getTitle().getTitle());
    }

    @Test
    public void saveCopyTest() {
        //Given

        //When
        copy1 = copyDbService.saveCopy(copy1);
        Long id = copy1.getId();
        Optional<Copy> copyOptional = copyDbService.getCopyById(id);

        //Then
        assertTrue(copyOptional.isPresent());
        assertEquals(id, copyOptional.get().getId());
        assertEquals("Test title 1", copyOptional.get().getTitle().getTitle());
    }

    @Test
    public void deleteCopyByIdTest() {
        //Given
        copyRepository.save(copy1);
        Long id = copy1.getId();

        //When
        Optional<Copy> copyBeforeDelete = copyDbService.getCopyById(id);
        copyDbService.deleteCopy(id);
        Optional<Copy> copyAfterDelete = copyDbService.getCopyById(id);

        //Then
        assertTrue(copyBeforeDelete.isPresent());
        assertEquals(id, copyBeforeDelete.get().getId());
        assertEquals("Test title 1", copyBeforeDelete.get().getTitle().getTitle());
        assertFalse(copyAfterDelete.isPresent());
    }
}
