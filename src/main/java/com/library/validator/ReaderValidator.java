package com.library.validator;

import com.library.domain.Borrow;
import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.domain.enums.BorrowArchiveReason;
import com.library.domain.enums.Role;
import com.library.exception.ReaderLoginHasAlreadyExistException;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.ReaderPasswordRequirementsNotFulfilException;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderDbService;
import com.library.validator.archive.BorrowArchiveValidator;
import com.library.validator.requirement.ReaderRequirementsChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReaderValidator {
    private final ReaderMapper readerMapper;
    private final ReaderDbService readerDbService;
    private final BorrowArchiveValidator borrowArchiveValidator;

    public List<ReaderDto> getAllReadersWithCleanPassword() {
        List<ReaderDto> readersDto = readerMapper.mapToReaderDtoList(readerDbService.getAllReaders());
        for (ReaderDto readerDto : readersDto) {
            readerDto.clearSensitive();
        }
        return readersDto;
    }

    public void checkRequirementsToCreateReaderAccount(ReaderDto readerDto)
            throws ReaderLoginHasAlreadyExistException, ReaderPasswordRequirementsNotFulfilException {
        List<Reader> readers = readerDbService.getAllReaders();
        readers.stream().forEach(reader -> reader.clearSensitive());

        if (ReaderRequirementsChecker.checkCorrectLogin(readerDto.getLogin(), readers)) {
            log.info("The given login exist");
            throw new ReaderLoginHasAlreadyExistException();
        } else if (!ReaderRequirementsChecker.checkCorrectPassword(readerDto.getPassword())) {
            log.info("The password requirements are not fulfiled");
            throw new ReaderPasswordRequirementsNotFulfilException();
        } else {
            readerDto.setCreateAccountDate(LocalDate.now().toString());
            readerDto.setRole(Role.NORMAL_READER);
            readerDbService.saveReader(readerMapper.mapToReader(readerDto, new ArrayList<>()));
            readerDto.clearSensitive();
        }
    }

    @Transactional
    public ReaderDto checkRequirementsToUpdateReaderAccount(ReaderDto readerDto) throws ReaderNotFoundException {
        Reader reader = readerDbService.getReaderById(readerDto.getId()).orElseThrow(ReaderNotFoundException::new);
        reader.clearSensitive();

        if ((!reader.getLogin().equals(readerDto.getLogin()))
                && (!reader.getCreateAccountDate().equals(readerDto.getCreateAccountDate()))
                && (reader.isBlocked() && !readerDto.isBlocked())
                && (!reader.getRole().equals(readerDto.getRole()))) {
            log.info("Reader cannot changed following elements: " +
                    "\nlogin" +
                    "\ncreate account date" +
                    "\nunblocked themselves" +
                    "\nchange their role" +
                    "Nothing will be changed");
            return readerMapper.mapToReaderDto(reader);
        }
        Hibernate.initialize(reader.getBorrows());
        Reader saveReader = readerDbService.saveReader(readerMapper.mapToReader(readerDto, reader.getBorrows()));
        saveReader.clearSensitive();

        return readerMapper.mapToReaderDto(saveReader);
    }

    @Transactional
    public void deleteReaderAccount(Long readerId) throws ReaderNotFoundException {
        Reader reader = readerDbService.getReaderById(readerId).orElseThrow(ReaderNotFoundException::new);
        reader.clearSensitive();

        Hibernate.initialize(reader.getBorrows());
        if (isAllBorrowFinish(reader.getBorrows())) {
            borrowArchiveValidator.archiveAllBorrows(reader.getBorrows(), BorrowArchiveReason.DELETE_READER);
            readerDbService.deleteById(readerId);
        } else {
            log.info("The reader account cannot be deleted. Reader has to return all borrowed books");
        }
    }

    private boolean isAllBorrowFinish(List<Borrow> borrows) {
        for (Borrow borrow : borrows) {
            if (!borrow.isFinish()) {
                return false;
            }
        }
        return true;
    }
}
