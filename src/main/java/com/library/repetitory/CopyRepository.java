package com.library.repetitory;

import com.library.domain.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {
    @Override
    public List<Copy> findAll();

    @Override
    public Optional<Copy> findById(Long copyId);

    @Override
    public Copy save(Copy copy);

    @Override
    public void deleteById(Long copyId);
}
