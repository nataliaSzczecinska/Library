package com.library.controller;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.dto.CopyDto;
import com.library.domain.dto.TitleDto;
import com.library.domain.enums.BookCategory;
import com.library.domain.enums.CopyStatus;
import com.library.facade.CopyFacade;
import com.library.mapper.CopyMapper;
import com.library.service.CopyDbService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig
@WebMvcTest(CopyController.class)
public class CopyControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CopyFacade copyFacade;

    @MockBean
    private CopyDbService copyDbService;

    @MockBean
    private CopyMapper copyMapper;

    @MockBean
    private TitleValidator titleValidator;

    private Title title = Title.builder()
            .id(1L)
            .title("Test title")
            .author("Test author")
            .publisher("Test publisher")
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
    private CopyDto copy2Dto = CopyDto.builder()
            .id(2L)
            .titleId(1L)
            .copyStatus(CopyStatus.AVAILABLE)
            .build();
    private CopyDto copy3Dto = CopyDto.builder()
            .id(3L)
            .titleId(1L)
            .copyStatus(CopyStatus.BORROWED)
            .build();

    @Test
    public void getAllCopiesTest() throws Exception {
        //Given
        List<Copy> copies = Arrays.asList(copy1, copy2, copy3);
        List<CopyDto> copiesDto = Arrays.asList(copy1Dto, copy2Dto, copy3Dto);

        when(copyMapper.mapToCopyDtoList(copies)).thenReturn(copiesDto);
        when(copyFacade.getAllCopies()).thenReturn(copiesDto);
        when(copyDbService.getAllCopies()).thenReturn(copies);

        //When&Then
        mockMvc.perform(get("/library/copies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].titleId", is(1)))
                .andExpect(jsonPath("$[0].copyStatus", is("AVAILABLE")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].titleId", is(1)))
                .andExpect(jsonPath("$[1].copyStatus", is("AVAILABLE")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].titleId", is(1)))
                .andExpect(jsonPath("$[2].copyStatus", is("BORROWED")));
    }

    @Test
    public void getCopyByIdTest() throws Exception {
        //Given
        when(copyMapper.mapToCopy(ArgumentMatchers.any(CopyDto.class), ArgumentMatchers.any(Title.class), ArgumentMatchers.any(List.class))).thenReturn(copy1);
        when(copyMapper.mapToCopyDto(copy1)).thenReturn(copy1Dto);
        when(copyFacade.getCopyById(1L)).thenReturn(copy1Dto);
        when(copyDbService.getCopyById(1L)).thenReturn(Optional.of(copy1));

        //When&Then
        mockMvc.perform(get("/library/copies/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titleId", is(1)))
                .andExpect(jsonPath("$.copyStatus", is("AVAILABLE")));
    }

    @Test
    public void getBooksByTitleTest() throws Exception {
        //Given
        List<Copy> copies = Arrays.asList(copy1, copy2, copy3);
        List<CopyDto> copiesDto = Arrays.asList(copy1Dto, copy2Dto, copy3Dto);

        when(copyMapper.mapToCopyDtoList(copies)).thenReturn(copiesDto);
        when(copyFacade.getBooksByTitle(1L)).thenReturn(copiesDto);
        when(copyDbService.getAllCopies()).thenReturn(copies);

        //When&Then
        mockMvc.perform(get("/library/copies/title/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].titleId", is(1)))
                .andExpect(jsonPath("$[0].copyStatus", is("AVAILABLE")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].titleId", is(1)))
                .andExpect(jsonPath("$[1].copyStatus", is("AVAILABLE")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].titleId", is(1)))
                .andExpect(jsonPath("$[2].copyStatus", is("BORROWED")));
    }

    @Test
    public void getAvailableCopiesByTitleTest() throws Exception {
        //Given
        List<Copy> copies = Arrays.asList(copy1, copy2);
        List<CopyDto> copiesDto = Arrays.asList(copy1Dto, copy2Dto);

        when(copyMapper.mapToCopyDtoList(copies)).thenReturn(copiesDto);
        when(copyFacade.getAvailableBooksByTitle(1L)).thenReturn(copiesDto);
        when(copyDbService.getAllCopies()).thenReturn(copies);

        //When&Then
        mockMvc.perform(get("/library/copies/title/1/available")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].titleId", is(1)))
                .andExpect(jsonPath("$[0].copyStatus", is("AVAILABLE")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].titleId", is(1)))
                .andExpect(jsonPath("$[1].copyStatus", is("AVAILABLE")));
    }

    @Test
    public void createCopyTest() throws Exception {
        //Given
        when(copyMapper.mapToCopy(ArgumentMatchers.any(CopyDto.class), ArgumentMatchers.any(Title.class), ArgumentMatchers.any(List.class))).thenReturn(copy1);
        when(copyMapper.mapToCopyDto(copy1)).thenReturn(copy1Dto);
        when(copyDbService.saveCopy(ArgumentMatchers.any(Copy.class))).thenReturn(copy1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(copy1Dto);

        //When & Then
        mockMvc.perform(post("/library/copies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCopyTest() throws Exception {
        //Given
        when(copyMapper.mapToCopy(ArgumentMatchers.any(CopyDto.class), ArgumentMatchers.any(Title.class), ArgumentMatchers.any(List.class))).thenReturn(copy1);
        when(copyMapper.mapToCopyDto(copy1)).thenReturn(copy1Dto);
        when(copyFacade.updateCopy(any(CopyDto.class))).thenReturn(copy1Dto);
        when(copyDbService.saveCopy(ArgumentMatchers.any(Copy.class))).thenReturn(copy1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(copy1Dto);

        //When & Then
        mockMvc.perform(put("/library/copies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titleId", is(1)))
                .andExpect(jsonPath("$.copyStatus", is("AVAILABLE")));
    }

    @Test
    public void deleteCopyTest() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/library/copies/1/LOST")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}
