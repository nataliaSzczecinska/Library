package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.domain.enums.Role;
import com.library.facade.ReaderFacade;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderDbService;
import com.library.validator.ReaderValidator;
import com.library.validator.requirement.ReaderRequirementsChecker;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig
@WebMvcTest(ReaderController.class)
public class ReaderControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderFacade readerFacade;

    @MockBean
    private ReaderDbService readerDbService;

    @MockBean
    private ReaderMapper readerMapper;

    @MockBean
    private ReaderValidator readerValidator;

    @MockBean
    private ReaderRequirementsChecker readerRequirementsChecker;

    private char[] password1 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '1'};
    private char[] password2 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'};
    private char[] password3 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '3'};

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

    private ReaderDto reader1Dto = ReaderDto.builder()
            .id(1L)
            .name("Test name 1")
            .login("Test login 1")
            .mailAddress("test1@mail.com")
            .password(password1)
            .createAccountDate("2001-01-01")
            .blocked(false)
            .role(Role.NORMAL_READER)
            .build();
    private ReaderDto reader2Dto = ReaderDto.builder()
            .id(2L)
            .name("Test name 2")
            .login("Test login 2")
            .mailAddress("test2@mail.com")
            .password(password2)
            .createAccountDate("2002-02-02")
            .blocked(false)
            .role(Role.MANAGER)
            .build();
    private ReaderDto reader3Dto = ReaderDto.builder()
            .id(3L)
            .name("Test name 3")
            .login("Test login 3")
            .mailAddress("test3@mail.com")
            .password(password3)
            .createAccountDate("2003-03-03")
            .blocked(true)
            .role(Role.ADMIN)
            .build();

    @Test
    public void getAllReadersTest() throws Exception {
        //Given
        List<Reader> readers = Arrays.asList(reader1, reader2, reader3);
        List<ReaderDto> readersDto = Arrays.asList(reader1Dto, reader2Dto, reader3Dto);

        when(readerMapper.mapToReaderDtoList(readers)).thenReturn(readersDto);
        when(readerFacade.getAllReaders()).thenReturn(readersDto);
        when(readerDbService.getAllReaders()).thenReturn(readers);

        //When&Then
        mockMvc.perform(get("/library/readers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test name 1")))
                .andExpect(jsonPath("$[0].login", is("Test login 1")))
                .andExpect(jsonPath("$[0].mailAddress", is("test1@mail.com")))
                .andExpect(jsonPath("$[0].createAccountDate", is("2001-01-01")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Test name 2")))
                .andExpect(jsonPath("$[1].login", is("Test login 2")))
                .andExpect(jsonPath("$[1].mailAddress", is("test2@mail.com")))
                .andExpect(jsonPath("$[1].createAccountDate", is("2002-02-02")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Test name 3")))
                .andExpect(jsonPath("$[2].login", is("Test login 3")))
                .andExpect(jsonPath("$[2].mailAddress", is("test3@mail.com")))
                .andExpect(jsonPath("$[2].createAccountDate", is("2003-03-03")));
    }

    @Test
    public void getReaderByIdTest() throws Exception {
        //Given
        when(readerMapper.mapToReaderDto(ArgumentMatchers.any(Reader.class))).thenReturn(reader1Dto);
        when(readerMapper.mapToReader(ArgumentMatchers.any(ReaderDto.class), ArgumentMatchers.eq(new ArrayList<>()))).thenReturn(reader1);
        when(readerFacade.getReaderById(1L)).thenReturn(reader1Dto);
        when(readerDbService.getReaderById(1L)).thenReturn(Optional.of(reader1));

        //When&Then
        mockMvc.perform(get("/library/readers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test name 1")))
                .andExpect(jsonPath("$.login", is("Test login 1")))
                .andExpect(jsonPath("$.mailAddress", is("test1@mail.com")))
                .andExpect(jsonPath("$.createAccountDate", is("2001-01-01")));
    }

    @Test
    public void createReaderTest() throws Exception {
        //Given
        List<Reader> readers = Arrays.asList(reader2, reader3);
        when(readerMapper.mapToReaderDto(reader1)).thenReturn(reader1Dto);
        when(readerMapper.mapToReader(reader1Dto, new ArrayList<>())).thenReturn(reader1);
        when(readerDbService.getAllReaders()).thenReturn(readers);
        when(readerDbService.saveReader(reader1)).thenReturn(reader1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(reader1Dto);

        //When & Then
        mockMvc.perform(post("/library/readers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateReaderTest() throws Exception {
        //Given
        when(readerMapper.mapToReaderDto(ArgumentMatchers.any(Reader.class))).thenReturn(reader1Dto);
        when(readerMapper.mapToReader(ArgumentMatchers.any(ReaderDto.class), ArgumentMatchers.eq(new ArrayList<>()))).thenReturn(reader1);
        when(readerDbService.saveReader(ArgumentMatchers.any(Reader.class))).thenReturn(reader1);
        when(readerFacade.updateReader(ArgumentMatchers.any(ReaderDto.class))).thenReturn(reader1Dto);
        when(readerValidator.checkRequirementsToUpdateReaderAccount(ArgumentMatchers.any(ReaderDto.class))).thenReturn(reader1Dto);

        Gson gson = new Gson();
        String jsContent = gson.toJson(reader1Dto);

        //When & Then
        mockMvc.perform(put("/library/readers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test name 1")))
                .andExpect(jsonPath("$.login", is("Test login 1")))
                .andExpect(jsonPath("$.mailAddress", is("test1@mail.com")))
                .andExpect(jsonPath("$.createAccountDate", is("2001-01-01")));
    }

    @Test
    public void blockedReaderTest() throws Exception {
        //Given
        when(readerMapper.mapToReaderDto(ArgumentMatchers.any(Reader.class))).thenReturn(reader1Dto);
        when(readerMapper.mapToReader(ArgumentMatchers.any(ReaderDto.class), ArgumentMatchers.eq(new ArrayList<>()))).thenReturn(reader1);
        when(readerDbService.saveReader(ArgumentMatchers.any(Reader.class))).thenReturn(reader1);
        when(readerFacade.blockedReader(1L)).thenReturn(reader1Dto);

        Gson gson = new Gson();
        String jsContent = gson.toJson(reader1Dto);

        //When & Then
        mockMvc.perform(put("/library/readers/1/blocked")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept("application/json")
                .content(jsContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test name 1")))
                .andExpect(jsonPath("$.login", is("Test login 1")))
                .andExpect(jsonPath("$.mailAddress", is("test1@mail.com")))
                .andExpect(jsonPath("$.createAccountDate", is("2001-01-01")));
    }

    @Test
    public void deleteReaderTest() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/library/readers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}
