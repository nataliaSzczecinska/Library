package com.library.facade;

import com.library.domain.Copy;
import com.library.domain.Title;
import com.library.domain.dto.CopyDto;
import com.library.domain.enums.BookCategory;
import com.library.domain.enums.CopyStatus;
import com.library.exception.CopyNotFoundException;
import com.library.exception.TitleNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CopyFacadeTestSuite {
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private CopyFacade copyFacade;

    private Title title = Title.builder()
            .title("Test title")
            .author("Test author")
            .publisher("Test publisher")
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
            .copyStatus(CopyStatus.BORROWED)
            .build();

    private CopyDto copy1Dto = CopyDto.builder()
            .copyStatus(CopyStatus.AVAILABLE)
            .build();

    @BeforeEach
    public void saveTitleInDatabase() {
        titleRepository.save(title);
    }

    @AfterEach
    public void cleanUp() {
        copyRepository.deleteAll();
        titleRepository.deleteAll();
    }

    @Test
    public void getAllCopiesTest() {
        //Given
        Long id = title.getId();
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<CopyDto> copies = copyFacade.getAllCopies();

        //Then
        assertEquals(3, copies.size());
        assertEquals(id, copies.get(0).getTitleId());
        assertEquals(id, copies.get(1).getTitleId());
        assertEquals(id, copies.get(2).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copies.get(0).getCopyStatus());
        assertEquals(CopyStatus.AVAILABLE, copies.get(1).getCopyStatus());
        assertEquals(CopyStatus.BORROWED, copies.get(2).getCopyStatus());
    }

    @Test
    public void getCopyByIdTest() throws CopyNotFoundException {
        //Given
        copyRepository.save(copy1);
        Long id = copy1.getId();


        //When
        CopyDto copyDto = copyFacade.getCopyById(id);

        //Then
        assertEquals(id, copyDto.getId());
        assertEquals(CopyStatus.AVAILABLE, copyDto.getCopyStatus());
    }

    @Test
    public void getAllCopiesByTitleTest() {
        //Given
        Long id = title.getId();
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<CopyDto> copies = copyFacade.getBooksByTitle(id);

        //Then
        assertEquals(3, copies.size());
        assertEquals(id, copies.get(0).getTitleId());
        assertEquals(id, copies.get(1).getTitleId());
        assertEquals(id, copies.get(2).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copies.get(0).getCopyStatus());
        assertEquals(CopyStatus.AVAILABLE, copies.get(1).getCopyStatus());
        assertEquals(CopyStatus.BORROWED, copies.get(2).getCopyStatus());
    }

    @Test
    public void getAvailableCopiesByTitleTest() {
        //Given
        Long id = title.getId();
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<CopyDto> copies = copyFacade.getAvailableBooksByTitle(id);

        //Then
        assertEquals(2, copies.size());
        assertEquals(id, copies.get(0).getTitleId());
        assertEquals(id, copies.get(1).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copies.get(0).getCopyStatus());
        assertEquals(CopyStatus.AVAILABLE, copies.get(1).getCopyStatus());
    }

    @Test
    public void createCopyTest() throws TitleNotFoundException {
        //Given
        Long id = title.getId();
        copy1Dto.setTitleId(id);
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<CopyDto> copiesBeforeAdd = copyFacade.getAllCopies();
        copyFacade.createCopy(copy1Dto);
        List<CopyDto> copiesAfterAdd = copyFacade.getAllCopies();

        //Then
        assertEquals(3, copiesBeforeAdd.size());
        assertEquals(id, copiesBeforeAdd.get(0).getTitleId());
        assertEquals(id, copiesBeforeAdd.get(1).getTitleId());
        assertEquals(id, copiesBeforeAdd.get(2).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copiesBeforeAdd.get(0).getCopyStatus());
        assertEquals(CopyStatus.AVAILABLE, copiesBeforeAdd.get(1).getCopyStatus());
        assertEquals(CopyStatus.BORROWED, copiesBeforeAdd.get(2).getCopyStatus());
        assertEquals(4, copiesAfterAdd.size());
        assertEquals(CopyStatus.AVAILABLE, copiesAfterAdd.get(3).getCopyStatus());
    }

    @Test
    public void updateCopyTest() throws TitleNotFoundException, CopyNotFoundException {
        //Given
        copyRepository.save(copy1);
        Long id = copy1.getId();

        //When
        CopyDto copyBeforeUpdate = copyFacade.getCopyById(id);
        CopyDto copyDto = CopyDto.builder()
                .id(copyBeforeUpdate.getId())
                .copyStatus(CopyStatus.BORROWED)
                .titleId(copyBeforeUpdate.getTitleId())
                .build();
        CopyDto copyAfterUpdate = copyFacade.updateCopy(copyDto);

        //Then
        assertEquals(id, copyBeforeUpdate.getId());
        assertEquals(CopyStatus.AVAILABLE, copyBeforeUpdate.getCopyStatus());
        assertEquals(id, copyAfterUpdate.getId());
        assertEquals(CopyStatus.BORROWED, copyAfterUpdate.getCopyStatus());
    }

    @Test
    public void deleteCopyTest() throws CopyNotFoundException {
        //Given
        Long id = title.getId();
        copyRepository.save(copy1);
        Long copyId = copy1.getId();
        copyRepository.save(copy2);
        copyRepository.save(copy3);

        //When
        List<CopyDto> copiesBeforeDelete = copyFacade.getAllCopies();
        copyFacade.deleteCopy(copyId, "LOST");
        List<CopyDto> copiesAfterDelete = copyFacade.getAllCopies();

        //Then
        assertEquals(3, copiesBeforeDelete.size());
        assertEquals(id, copiesBeforeDelete.get(0).getTitleId());
        assertEquals(id, copiesBeforeDelete.get(1).getTitleId());
        assertEquals(id, copiesBeforeDelete.get(2).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copiesBeforeDelete.get(0).getCopyStatus());
        assertEquals(CopyStatus.AVAILABLE, copiesBeforeDelete.get(1).getCopyStatus());
        assertEquals(CopyStatus.BORROWED, copiesBeforeDelete.get(2).getCopyStatus());
        assertEquals(2, copiesAfterDelete.size());
        assertEquals(id, copiesAfterDelete.get(0).getTitleId());
        assertEquals(id, copiesAfterDelete.get(1).getTitleId());
        assertEquals(CopyStatus.AVAILABLE, copiesAfterDelete.get(0).getCopyStatus());
        assertEquals(CopyStatus.BORROWED, copiesAfterDelete.get(1).getCopyStatus());
    }
}
