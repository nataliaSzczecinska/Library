package com.library.facade;

import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import com.library.domain.enums.BookCategory;
import com.library.exception.TitleNotFoundException;
import com.library.repetitory.TitleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TitleFacadeTestSuite {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private TitleFacade titleFacade;

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
            .categories(Arrays.asList(BookCategory.THRILLER, BookCategory.DOCUMENT, BookCategory.BIOGRAPHY))
            .build();

    private TitleDto title3Dto = TitleDto.builder()
            .title("Test title 3")
            .author("Test author 3")
            .publisher("Test publisher 3")
            .year(2003)
            .categories(Arrays.asList(BookCategory.THRILLER, BookCategory.DOCUMENT, BookCategory.BIOGRAPHY))
            .build();

    @AfterEach
    public void cleanUp() {
        titleRepository.deleteAll();
    }

    @Test
    public void getAllBookTitlesTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<TitleDto> titles = titleFacade.getAllBookTitles();

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void getExistBookTitleByIdTest() throws TitleNotFoundException {
        //Given
        titleRepository.save(title1);
        Long id = title1.getId();

        //When
        TitleDto titleDto = titleFacade.getBookTitleById(id);

        //Then
        assertEquals(id, titleDto.getId());
        assertEquals("Test title 1", titleDto.getTitle());
        assertEquals("Test author 1", titleDto.getAuthor());
        assertEquals("Test publisher 1", titleDto.getPublisher());
        assertEquals(2001, titleDto.getYear());
        assertEquals(2, titleDto.getCategories().size());
    }

    @Test
    public void getBooksByTitleFragmentTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<TitleDto> titles = titleFacade.getBooksByTitleFragment("itle");

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void getBooksByAuthorTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<TitleDto> titles = titleFacade.getBooksByAuthor("thor");

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void getBooksByPublisherTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<TitleDto> titles = titleFacade.getBooksByPublisher("lish");

        //Then
        assertEquals(3, titles.size());
        assertEquals("Test title 1", titles.get(0).getTitle());
        assertEquals("Test title 2", titles.get(1).getTitle());
        assertEquals("Test title 3", titles.get(2).getTitle());
    }

    @Test
    public void getBooksByYearTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<TitleDto> titles = titleFacade.getBooksByYear(2003);

        //Then
        assertEquals(1, titles.size());
        assertEquals("Test title 3", titles.get(0).getTitle());
    }

    @Test
    public void getBooksByCategoryTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //When
        List<TitleDto> titles = titleFacade.getBooksByCategory("BIOGRAPHY");

        //Then
        assertEquals(2, titles.size());
    }

    @Test
    public void createBookTitleTest() {
        //Given
        titleRepository.save(title1);
        titleRepository.save(title2);

        //When
        List<TitleDto> titlesBeforeCreation = titleFacade.getAllBookTitles();
        titleFacade.createBookTitle(title3Dto);
        List<TitleDto> titlesAfterCreation = titleFacade.getAllBookTitles();

        //Then
        assertEquals(2, titlesBeforeCreation.size());
        assertEquals(3, titlesAfterCreation.size());
    }

    @Test
    public void updateBookTitleTest() throws TitleNotFoundException {
        //Given
        titleRepository.save(title1);
        Long id = title1.getId();
        title3Dto.setId(id);
        title3Dto.setAuthor("Update author");
        title3Dto.setTitle("Update title");
        title3Dto.setPublisher("Update publisher");
        title3Dto.setYear(2013);

        System.out.println("Id = " + id);
        System.out.println("Id from title1 = " + title1.getId());
        System.out.println("Id from title3Dto = " + title3Dto.getId());

        //When
        TitleDto titleBeforeUpdate = titleFacade.getBookTitleById(id);
        titleFacade.updateBookTitle(title3Dto);
        TitleDto titleAfterUpdate = titleFacade.getBookTitleById(id);

        System.out.println("Id = " + id);
        System.out.println("Id from title1 = " + title1.getId());
        System.out.println("Id from title3Dto = " + title3Dto.getId());

        //Then
        assertEquals(id, titleBeforeUpdate.getId());
        assertEquals("Test title 1", titleBeforeUpdate.getTitle());
        assertEquals("Test author 1", titleBeforeUpdate.getAuthor());
        assertEquals("Test publisher 1", titleBeforeUpdate.getPublisher());
        assertEquals(2001, titleBeforeUpdate.getYear());
        assertEquals(id, titleAfterUpdate.getId());
        assertEquals("Update title", titleAfterUpdate.getTitle());
        assertEquals("Update author", titleAfterUpdate.getAuthor());
        assertEquals("Update publisher", titleAfterUpdate.getPublisher());
        assertEquals(2013, titleAfterUpdate.getYear());
    }
}
