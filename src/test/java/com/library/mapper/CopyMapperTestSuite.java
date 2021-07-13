package com.library.mapper;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.dto.CopyDto;
import com.library.domain.enums.BookCategory;
import com.library.domain.enums.CopyStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CopyMapperTestSuite {
    @Autowired
    private CopyMapper copyMapper;

    private Title title = Title.builder()
            .id(1L)
            .title("Test title 1")
            .author("Test author 1")
            .publisher("Test publisher 1")
            .year(2001)
            .categories(Arrays.asList(BookCategory.ACTION, BookCategory.BIOGRAPHY))
            .build();

    private Copy copy1 = Copy.builder()
            .id(1L)
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy2 = Copy.builder()
            .id(2L)
            .title(title)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private Copy copy3 = Copy.builder()
            .id(3L)
            .title(title)
            .copyStatus(CopyStatus.BORROWED)
            .build();

    private CopyDto copy1Dto = CopyDto.builder()
            .id(1L)
            .titleId(1L)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();

    @Test
    public void mapToCopyTest() {
        //Given

        //When
        Copy copy = copyMapper.mapToCopy(copy1Dto, title, new ArrayList<>());

        //Then
        assertEquals(1L, copy.getId());
        assertEquals(1L, copy.getTitle().getId());
        assertEquals("Test title 1", copy.getTitle().getTitle());
        assertEquals("Test author 1", copy.getTitle().getAuthor());
        assertEquals("Test publisher 1", copy.getTitle().getPublisher());
        assertEquals(2001, copy.getTitle().getYear());
        assertEquals(2, copy.getTitle().getCategories().size());
        assertEquals(CopyStatus.AVAILABLE, copy.getCopyStatus());
    }

    @Test
    public void mapToCopyDtoTest() {
        //Given

        //When
        CopyDto copyDto = copyMapper.mapToCopyDto(copy1);

        //Then
        assertEquals(1L, copyDto.getId());
        assertEquals(1L, copyDto.getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copyDto.getCopyStatus());
    }

    @Test
    public void mapToCopyDtoListTest() {
        //Given
        List<Copy> copies = Arrays.asList(copy1, copy2, copy3);

        //When
        List<CopyDto> copyDtoList = copyMapper.mapToCopyDtoList(copies);

        //Then
        assertEquals(3, copyDtoList.size());
        assertEquals(1L, copyDtoList.get(0).getId());
        assertEquals(1L, copyDtoList.get(0).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copyDtoList.get(0).getCopyStatus());
        assertEquals(2L, copyDtoList.get(1).getId());
        assertEquals(1L, copyDtoList.get(1).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copyDtoList.get(1).getCopyStatus());
        assertEquals(3L, copyDtoList.get(2).getId());
        assertEquals(1L, copyDtoList.get(2).getTitleId());
        assertEquals(CopyStatus.BORROWED, copyDtoList.get(2).getCopyStatus());
    }

}
