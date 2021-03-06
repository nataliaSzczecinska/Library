package com.library.domain;

import com.library.domain.enums.BookCategory;
import com.library.facade.sql.SQLCondition;
import com.library.repetitory.TitleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TitleTestSuite {
    @Autowired
    private TitleRepository titleRepository;

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
    private Title title3 = Title.builder()
            .title("Test title 3")
            .author("Test author 3")
            .publisher("Test publisher 3")
            .year(2003)
            .categories(Arrays.asList(BookCategory.THRILLER, BookCategory.DOCUMENT))
            .build();

    @AfterEach
    public void cleanUp() {
        titleRepository.deleteAll();
    }

    @Test
    public void saveTitleToDatabaseTest() {
        //Given

        //When
        titleRepository.save(title1);
        Long id = title1.getId();
        Optional<Title> titleOptional = titleRepository.findById(id);

        //Then
        assertTrue(titleOptional.isPresent());
    }

    @Test
    public void findAllTitlesTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<Title> titles = titleRepository.findAll();

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void findTitleByIdTest() {
        //Given
        titleRepository.save(title1);
        Long id = title1.getId();

        //When
        Optional<Title> titleOptional = titleRepository.findById(id);

        //Then
        assertTrue(titleOptional.isPresent());
        assertEquals(id, titleOptional.get().getId());
    }

    @Test
    public void retrieveBookByTitleFragmentTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<Title> titles = titleRepository.retrieveBookByTitleFragment(SQLCondition.sqlCondition("itl"));

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void retrieveBookByAuthorTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<Title> titles = titleRepository.retrieveTitleByAuthor(SQLCondition.sqlCondition("thor"));

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void retrieveBookByPublisherTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<Title> titles = titleRepository.retrieveTitleByPublisher(SQLCondition.sqlCondition("lish"));

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void retrieveBookByYearTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<Title> titles = titleRepository.retrieveTitleByYear(2003);

        //Then
        assertEquals(1, titles.size());
        assertEquals("Test title 3", titles.get(0).getTitle());
    }
}
