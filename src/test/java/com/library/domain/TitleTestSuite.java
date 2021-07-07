package com.library.domain;

import com.library.domain.enums.BookCategory;
import com.library.repetitory.TitleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

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
    public void saveTitleToDatabase() {
        //Given

        //When
        titleRepository.save(title1);
        Long id = title1.getId();
        Optional<Title> titleOptional = titleRepository.findById(id);

        //Then
        assertTrue(titleOptional.isPresent());
    }
}
