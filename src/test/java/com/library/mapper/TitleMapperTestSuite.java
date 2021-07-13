package com.library.mapper;

import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import com.library.domain.enums.BookCategory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TitleMapperTestSuite {
    @Autowired
    private TitleMapper titleMapper;

    private Title title1 = Title.builder()
            .id(1L)
            .title("Test title 1")
            .author("Test author 1")
            .publisher("Test publisher 1")
            .year(2001)
            .categories(Arrays.asList(BookCategory.ACTION, BookCategory.BIOGRAPHY))
            .build();
    private Title title2 = Title.builder()
            .id(2L)
            .title("Test title 2")
            .author("Test author 2")
            .publisher("Test publisher 2")
            .year(2002)
            .categories(Arrays.asList(BookCategory.ADVENTURE, BookCategory.COMEDY, BookCategory.ROMANCE))
            .build();
    private Title title3 = Title.builder()
            .id(3L)
            .title("Test title 3")
            .author("Test author 3")
            .publisher("Test publisher 3")
            .year(2003)
            .categories(Arrays.asList(BookCategory.THRILLER, BookCategory.DOCUMENT))
            .build();

    private TitleDto title1Dto = TitleDto.builder()
            .id(1L)
            .title("Test title 1")
            .author("Test author 1")
            .publisher("Test publisher 1")
            .year(2001)
            .categories(Arrays.asList(BookCategory.ACTION, BookCategory.BIOGRAPHY))
            .build();

    @Test
    public void mapToTitleTest() {
        //Given

        //When
        Title title = titleMapper.mapToTitle(title1Dto, new ArrayList<>(), new ArrayList<>());

        //Then
        assertEquals(1L, title.getId());
        assertEquals("Test title 1", title.getTitle());
        assertEquals("Test author 1", title.getAuthor());
        assertEquals("Test publisher 1", title.getPublisher());
        assertEquals(2001, title.getYear());
        assertEquals(2, title.getCategories().size());
    }

    @Test
    public void mapToTitleDtoTest() {
        //Given

        //When
        TitleDto titleDto = titleMapper.mapToTitleDto(title1);

        //Then
        assertEquals(1L, titleDto.getId());
        assertEquals("Test title 1", titleDto.getTitle());
        assertEquals("Test author 1", titleDto.getAuthor());
        assertEquals("Test publisher 1", titleDto.getPublisher());
        assertEquals(2001, titleDto.getYear());
        assertEquals(2, titleDto.getCategories().size());
    }

    @Test
    public void mapToTitleDtoListTest() {
        //Given
        List<Title> titles = Arrays.asList(title1, title2, title3);

        //When
        List<TitleDto> titleDtoList = titleMapper.mapToTitleDtoList(titles);

        //Then
        assertEquals(3, titleDtoList.size());
        assertEquals(1L, titleDtoList.get(0).getId());
        assertEquals("Test title 1", titleDtoList.get(0).getTitle());
        assertEquals("Test author 1", titleDtoList.get(0).getAuthor());
        assertEquals("Test publisher 1", titleDtoList.get(0).getPublisher());
        assertEquals(2001, titleDtoList.get(0).getYear());
        assertEquals(2, titleDtoList.get(0).getCategories().size());
        assertEquals(2L, titleDtoList.get(1).getId());
        assertEquals("Test title 2", titleDtoList.get(1).getTitle());
        assertEquals("Test author 2", titleDtoList.get(1).getAuthor());
        assertEquals("Test publisher 2", titleDtoList.get(1).getPublisher());
        assertEquals(2002, titleDtoList.get(1).getYear());
        assertEquals(3, titleDtoList.get(1).getCategories().size());
        assertEquals(3L, titleDtoList.get(2).getId());
        assertEquals("Test title 3", titleDtoList.get(2).getTitle());
        assertEquals("Test author 3", titleDtoList.get(2).getAuthor());
        assertEquals("Test publisher 3", titleDtoList.get(2).getPublisher());
        assertEquals(2003, titleDtoList.get(2).getYear());
        assertEquals(2, titleDtoList.get(2).getCategories().size());
    }
}
