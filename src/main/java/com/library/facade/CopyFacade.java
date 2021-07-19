package com.library.facade;

import com.library.domain.dto.CopyDto;
import com.library.domain.enums.CopyStatus;
import com.library.exception.CopyNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.mapper.CopyMapper;
import com.library.service.CopyDbService;
import com.library.service.TitleDbService;
import com.library.validator.CopyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CopyFacade {
    private final CopyMapper copyMapper;
    private final CopyDbService copyDbService;
    private final TitleDbService titleDbService;
    private final CopyValidator copyValidator;

    public List<CopyDto> getAllCopies() {
        return copyMapper.mapToCopyDtoList(copyDbService.getAllCopies());
    }

    public CopyDto getCopyById(Long copyId) throws CopyNotFoundException {
        return copyMapper.mapToCopyDto(copyDbService.getCopyById(copyId).orElseThrow(CopyNotFoundException::new));
    }

    public List<CopyDto> getBooksByTitle(Long titleId) {
        return copyMapper.mapToCopyDtoList(copyDbService.getAllCopies().stream()
                                        .filter(copy -> titleId.equals(copy.getTitle().getId()))
                                        .collect(Collectors.toList()));
    }
    public List<CopyDto> getAvailableBooksByTitle(Long titleId) {
        return getBooksByTitle(titleId).stream()
                .filter(copyDto -> copyDto.getCopyStatus().equals(CopyStatus.AVAILABLE))
                .collect(Collectors.toList());
    }

    public void createCopy(CopyDto copyDto) throws TitleNotFoundException {
        copyDbService.saveCopy(copyMapper.mapToCopy(copyDto,
                titleDbService.getTitleById(copyDto.getTitleId()).orElseThrow(TitleNotFoundException::new),
                new ArrayList<>()));
    }

    public CopyDto updateCopy(CopyDto copyDto) throws TitleNotFoundException, CopyNotFoundException {
        return copyMapper.mapToCopyDto(copyDbService.saveCopy(copyMapper.mapToCopy(copyDto,
                titleDbService.getTitleById(copyDto.getTitleId()).orElseThrow(TitleNotFoundException::new),
                copyDbService.getCopyById(copyDto.getId()).orElseThrow(CopyNotFoundException::new).getBorrows()
        )));
    }

    public void deleteCopy(Long copyId, String reason) throws CopyNotFoundException {
        copyValidator.deleteCopy(copyId, reason);
    }
}
