package com.library.domain;

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
public class CopyTestSuite {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CopyRepository copyRepository;

    private Title title1 = Title.builder()
            .title("Test title 1")
            .author("Test author 1")
            .publisher("Test publisher 1")
            .year(2001)
            .categories(Arrays.asList(BookCategory.ACTION, BookCategory.BIOGRAPHY))
            .build();
    private Title title2 = Title.builder()
            .title("Test title 2")
            .author("Test author 2")
            .publisher("Test publisher 2")
            .year(2002)
            .categories(Arrays.asList(BookCategory.ADVENTURE, BookCategory.COMEDY, BookCategory.ROMANCE))
            .build();

    private Copy copy1 = Copy.builder()
            .title(title1)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy2 = Copy.builder()
            .title(title1)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy3 = Copy.builder()
            .title(title2)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();

    @BeforeEach
    public void saveTitles() {
        titleRepository.save(title1);
        titleRepository.save(title2);
    }

    @AfterEach
    public void clearUpRepositories() {
        copyRepository.deleteAll();
        titleRepository.deleteAll();
    }

    @Test
    public void saveCopyInDatabaseTest() {
        //Given

        //When
        copyRepository.save(copy1);
        Long id = copy1.getId();
        Optional<Copy> copyOptional = copyRepository.findById(id);

        //Then
        assertTrue(copyOptional.isPresent());
        assertEquals(id, copyOptional.get().getId());
        assertEquals(title1.getId(), copyOptional.get().getTitle().getId());
    }

    @Test
    public void findAllCopiesTest() {
        //Given
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<Copy> copies = copyRepository.findAll();

        //Then
        assertEquals(3, copies.size());
        assertEquals("Test title 1", copies.get(0).getTitle().getTitle());
        assertEquals("Test title 1", copies.get(1).getTitle().getTitle());
        assertEquals("Test title 2", copies.get(2).getTitle().getTitle());
    }

    @Test
    public void findCopyByIdTest() {
        //Given
        copyRepository.save(copy1);
        Long id = copy1.getId();

        //When
        Optional<Copy> copyOptional = copyRepository.findById(id);

        //Then
        assertTrue(copyOptional.isPresent());
        assertEquals(id, copyOptional.get().getId());
    }

    @Test
    public void deleteCopyByIdTest() {
        //Given
        copyRepository.save(copy1);
        Long id = copy1.getId();

        //When
        Optional<Copy> copyBeforeDelete = copyRepository.findById(id);
        copyRepository.deleteById(id);
        Optional<Copy> copyAfterDelete = copyRepository.findById(id);

        //Then
        assertTrue(copyBeforeDelete.isPresent());
        assertEquals(id, copyBeforeDelete.get().getId());
        assertFalse(copyAfterDelete.isPresent());
    }
}
