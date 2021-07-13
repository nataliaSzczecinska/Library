package com.library.repetitory.archive;

import com.library.domain.archive.DeleteCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DeleteCopyRepository extends CrudRepository<DeleteCopy, Long> {
    @Override
    public List<DeleteCopy> findAll();

    @Override
    public Optional<DeleteCopy> findById(Long deleteCopyId);

    @Override
    public DeleteCopy save(DeleteCopy deleteCopy);
}
