package com.library.controller;

import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import com.library.domain.enums.BookCategory;
import com.library.facade.TitleFacade;
import com.library.facade.sql.SQLCondition;
import com.library.mapper.TitleMapper;
import com.library.service.TitleDbService;
import com.library.validator.TitleValidator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig
@WebMvcTest(TitleController.class)
public class TitleControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TitleFacade titleFacade;

    @MockBean
    private TitleDbService titleDbService;

    @MockBean
    private TitleMapper titleMapper;

    @MockBean
    private TitleValidator titleValidator;

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
    private TitleDto title2Dto = TitleDto.builder()
            .id(2L)
            .title("Test title 2")
            .author("Test author 2")
            .publisher("Test publisher 2")
            .year(2002)
            .categories(Arrays.asList(BookCategory.ADVENTURE, BookCategory.COMEDY, BookCategory.ROMANCE))
            .build();
    private TitleDto title3Dto = TitleDto.builder()
            .id(3L)
            .title("Test title 3")
            .author("Test author 3")
            .publisher("Test publisher 3")
            .year(2003)
            .categories(Arrays.asList(BookCategory.THRILLER, BookCategory.DOCUMENT))
            .build();

    @Test
    public void getAllTitlesTest() throws Exception {
        //Given
        List<Title> titles = Arrays.asList(title1, title2, title3);
        List<TitleDto> titlesDto = Arrays.asList(title1Dto, title2Dto, title3Dto);

        when(titleMapper.mapToTitleDtoList(titles)).thenReturn(titlesDto);
        when(titleFacade.getAllBookTitles()).thenReturn(titlesDto);
        when(titleDbService.getAllTitles()).thenReturn(titles);

        //When&Then
        mockMvc.perform(get("/library/titles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title 1")))
                .andExpect(jsonPath("$[0].author", is("Test author 1")))
                .andExpect(jsonPath("$[0].publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$[0].year", is(2001)))
                .andExpect(jsonPath("$[0].categories", hasSize(2)))
                .andExpect(jsonPath("$[0].categories[0]", is("ACTION")))
                .andExpect(jsonPath("$[0].categories[1]", is("BIOGRAPHY")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test title 2")))
                .andExpect(jsonPath("$[1].author", is("Test author 2")))
                .andExpect(jsonPath("$[1].publisher", is("Test publisher 2")))
                .andExpect(jsonPath("$[1].year", is(2002)))
                .andExpect(jsonPath("$[1].categories", hasSize(3)))
                .andExpect(jsonPath("$[1].categories[0]", is("ADVENTURE")))
                .andExpect(jsonPath("$[1].categories[1]", is("COMEDY")))
                .andExpect(jsonPath("$[1].categories[2]", is("ROMANCE")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("Test title 3")))
                .andExpect(jsonPath("$[2].author", is("Test author 3")))
                .andExpect(jsonPath("$[2].publisher", is("Test publisher 3")))
                .andExpect(jsonPath("$[2].year", is(2003)))
                .andExpect(jsonPath("$[2].categories", hasSize(2)))
                .andExpect(jsonPath("$[2].categories[0]", is("THRILLER")))
                .andExpect(jsonPath("$[2].categories[1]", is("DOCUMENT")));
    }

    @Test
    public void getBookTitleByIdTest() throws Exception {
        //Given
        when(titleMapper.mapToTitleDto(title1)).thenReturn(title1Dto);
        when(titleMapper.mapToTitle(title1Dto, new ArrayList<>())).thenReturn(title1);
        when(titleFacade.getBookTitleById(1L)).thenReturn(title1Dto);
        when(titleDbService.getTitleById(1L)).thenReturn(Optional.of(title1));

        //When&Then
        mockMvc.perform(get("/library/titles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title 1")))
                .andExpect(jsonPath("$.author", is("Test author 1")))
                .andExpect(jsonPath("$.publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$.year", is(2001)))
                .andExpect(jsonPath("$.categories", hasSize(2)))
                .andExpect(jsonPath("$.categories[0]", is("ACTION")))
                .andExpect(jsonPath("$.categories[1]", is("BIOGRAPHY")));
    }

    @Test
    public void getBooksByTitleFragmentTest() throws Exception {
        //Given
        List<Title> titles = Arrays.asList(title1, title2, title3);
        List<TitleDto> titlesDto = Arrays.asList(title1Dto, title2Dto, title3Dto);

        when(titleMapper.mapToTitleDtoList(titles)).thenReturn(titlesDto);
        when(titleFacade.getBooksByTitleFragment("itl")).thenReturn(titlesDto);
        when(titleDbService.searchBookTitlesByTitleFragment(SQLCondition.sqlCondition("itl"))).thenReturn(titles);

        //When&Then
        mockMvc.perform(get("/library/titles/title/itl")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title 1")))
                .andExpect(jsonPath("$[0].author", is("Test author 1")))
                .andExpect(jsonPath("$[0].publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$[0].year", is(2001)))
                .andExpect(jsonPath("$[0].categories", hasSize(2)))
                .andExpect(jsonPath("$[0].categories[0]", is("ACTION")))
                .andExpect(jsonPath("$[0].categories[1]", is("BIOGRAPHY")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test title 2")))
                .andExpect(jsonPath("$[1].author", is("Test author 2")))
                .andExpect(jsonPath("$[1].publisher", is("Test publisher 2")))
                .andExpect(jsonPath("$[1].year", is(2002)))
                .andExpect(jsonPath("$[1].categories", hasSize(3)))
                .andExpect(jsonPath("$[1].categories[0]", is("ADVENTURE")))
                .andExpect(jsonPath("$[1].categories[1]", is("COMEDY")))
                .andExpect(jsonPath("$[1].categories[2]", is("ROMANCE")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("Test title 3")))
                .andExpect(jsonPath("$[2].author", is("Test author 3")))
                .andExpect(jsonPath("$[2].publisher", is("Test publisher 3")))
                .andExpect(jsonPath("$[2].year", is(2003)))
                .andExpect(jsonPath("$[2].categories", hasSize(2)))
                .andExpect(jsonPath("$[2].categories[0]", is("THRILLER")))
                .andExpect(jsonPath("$[2].categories[1]", is("DOCUMENT")));
    }

    @Test
    public void getBooksByAuthorTest() throws Exception {
        //Given
        List<Title> titles = Arrays.asList(title1, title2, title3);
        List<TitleDto> titlesDto = Arrays.asList(title1Dto, title2Dto, title3Dto);

        when(titleMapper.mapToTitleDtoList(titles)).thenReturn(titlesDto);
        when(titleFacade.getBooksByAuthor("thor")).thenReturn(titlesDto);
        when(titleDbService.searchBookTitlesByAuthor(SQLCondition.sqlCondition("thor"))).thenReturn(titles);

        //When&Then
        mockMvc.perform(get("/library/titles/author/thor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title 1")))
                .andExpect(jsonPath("$[0].author", is("Test author 1")))
                .andExpect(jsonPath("$[0].publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$[0].year", is(2001)))
                .andExpect(jsonPath("$[0].categories", hasSize(2)))
                .andExpect(jsonPath("$[0].categories[0]", is("ACTION")))
                .andExpect(jsonPath("$[0].categories[1]", is("BIOGRAPHY")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test title 2")))
                .andExpect(jsonPath("$[1].author", is("Test author 2")))
                .andExpect(jsonPath("$[1].publisher", is("Test publisher 2")))
                .andExpect(jsonPath("$[1].year", is(2002)))
                .andExpect(jsonPath("$[1].categories", hasSize(3)))
                .andExpect(jsonPath("$[1].categories[0]", is("ADVENTURE")))
                .andExpect(jsonPath("$[1].categories[1]", is("COMEDY")))
                .andExpect(jsonPath("$[1].categories[2]", is("ROMANCE")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("Test title 3")))
                .andExpect(jsonPath("$[2].author", is("Test author 3")))
                .andExpect(jsonPath("$[2].publisher", is("Test publisher 3")))
                .andExpect(jsonPath("$[2].year", is(2003)))
                .andExpect(jsonPath("$[2].categories", hasSize(2)))
                .andExpect(jsonPath("$[2].categories[0]", is("THRILLER")))
                .andExpect(jsonPath("$[2].categories[1]", is("DOCUMENT")));
    }

    @Test
    public void getBooksByPublisherTest() throws Exception {
        //Given
        List<Title> titles = Arrays.asList(title1, title2, title3);
        List<TitleDto> titlesDto = Arrays.asList(title1Dto, title2Dto, title3Dto);

        when(titleMapper.mapToTitleDtoList(titles)).thenReturn(titlesDto);
        when(titleFacade.getBooksByPublisher("lishe")).thenReturn(titlesDto);
        when(titleDbService.searchBookTitlesByPublisher(SQLCondition.sqlCondition("lishe"))).thenReturn(titles);

        //When&Then
        mockMvc.perform(get("/library/titles/publisher/lishe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title 1")))
                .andExpect(jsonPath("$[0].author", is("Test author 1")))
                .andExpect(jsonPath("$[0].publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$[0].year", is(2001)))
                .andExpect(jsonPath("$[0].categories", hasSize(2)))
                .andExpect(jsonPath("$[0].categories[0]", is("ACTION")))
                .andExpect(jsonPath("$[0].categories[1]", is("BIOGRAPHY")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test title 2")))
                .andExpect(jsonPath("$[1].author", is("Test author 2")))
                .andExpect(jsonPath("$[1].publisher", is("Test publisher 2")))
                .andExpect(jsonPath("$[1].year", is(2002)))
                .andExpect(jsonPath("$[1].categories", hasSize(3)))
                .andExpect(jsonPath("$[1].categories[0]", is("ADVENTURE")))
                .andExpect(jsonPath("$[1].categories[1]", is("COMEDY")))
                .andExpect(jsonPath("$[1].categories[2]", is("ROMANCE")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("Test title 3")))
                .andExpect(jsonPath("$[2].author", is("Test author 3")))
                .andExpect(jsonPath("$[2].publisher", is("Test publisher 3")))
                .andExpect(jsonPath("$[2].year", is(2003)))
                .andExpect(jsonPath("$[2].categories", hasSize(2)))
                .andExpect(jsonPath("$[2].categories[0]", is("THRILLER")))
                .andExpect(jsonPath("$[2].categories[1]", is("DOCUMENT")));
    }

    @Test
    public void getBooksByYearTest() throws Exception {
        //Given
        List<Title> titles = Arrays.asList(title1);
        List<TitleDto> titlesDto = Arrays.asList(title1Dto);

        when(titleMapper.mapToTitleDtoList(titles)).thenReturn(titlesDto);
        when(titleFacade.getBooksByYear(2001)).thenReturn(titlesDto);
        when(titleDbService.searchBookTitlesByYear(2001)).thenReturn(titles);

        //When&Then
        mockMvc.perform(get("/library/titles/year/2001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title 1")))
                .andExpect(jsonPath("$[0].author", is("Test author 1")))
                .andExpect(jsonPath("$[0].publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$[0].year", is(2001)))
                .andExpect(jsonPath("$[0].categories", hasSize(2)))
                .andExpect(jsonPath("$[0].categories[0]", is("ACTION")))
                .andExpect(jsonPath("$[0].categories[1]", is("BIOGRAPHY")));
    }

    @Test
    public void getBooksByCategoryTest() throws Exception {
        //Given
        List<Title> titles = Arrays.asList(title1, title2, title3);
        List<Title> titlesCategory = Arrays.asList(title1);
        List<TitleDto> titlesDto = Arrays.asList(title1Dto);

        when(titleMapper.mapToTitleDtoList(titles)).thenReturn(titlesDto);
        when(titleFacade.getBooksByCategory("BIOGRAPHY")).thenReturn(titlesDto);
        when(titleDbService.getAllTitles()).thenReturn(titles);
        when(titleValidator.findBooksByCategory(titles, "BIOGRAPHY")).thenReturn(titlesCategory);

        //When&Then
        mockMvc.perform(get("/library/titles/category/BIOGRAPHY")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title 1")))
                .andExpect(jsonPath("$[0].author", is("Test author 1")))
                .andExpect(jsonPath("$[0].publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$[0].year", is(2001)))
                .andExpect(jsonPath("$[0].categories", hasSize(2)))
                .andExpect(jsonPath("$[0].categories[0]", is("ACTION")))
                .andExpect(jsonPath("$[0].categories[1]", is("BIOGRAPHY")));
    }

    @Test
    public void createBookTitleTest() throws Exception {
        //Given
        when(titleMapper.mapToTitleDto(ArgumentMatchers.any(Title.class))).thenReturn(title1Dto);
        when(titleMapper.mapToTitle(ArgumentMatchers.any(TitleDto.class), ArgumentMatchers.eq(new ArrayList<>()))).thenReturn(title1);
        when(titleDbService.saveTitle(ArgumentMatchers.any(Title.class))).thenReturn(title1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(title1Dto);

        //When & Then
        mockMvc.perform(post("/library/titles")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBookTitleTest() throws Exception {
        //Given
        when(titleMapper.mapToTitleDto(ArgumentMatchers.any(Title.class))).thenReturn(title1Dto);
        when(titleMapper.mapToTitle(ArgumentMatchers.any(TitleDto.class), ArgumentMatchers.eq(new ArrayList<>()))).thenReturn(title1);
        when(titleFacade.updateBookTitle(ArgumentMatchers.any(TitleDto.class))).thenReturn(title1Dto);
        when(titleDbService.saveTitle(ArgumentMatchers.any(Title.class))).thenReturn(title1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(title1Dto);

        //When & Then
        mockMvc.perform(put("/library/titles")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title 1")))
                .andExpect(jsonPath("$.author", is("Test author 1")))
                .andExpect(jsonPath("$.publisher", is("Test publisher 1")))
                .andExpect(jsonPath("$.year", is(2001)))
                .andExpect(jsonPath("$.categories", hasSize(2)))
                .andExpect(jsonPath("$.categories[0]", is("ACTION")))
                .andExpect(jsonPath("$.categories[1]", is("BIOGRAPHY")));
    }
}
