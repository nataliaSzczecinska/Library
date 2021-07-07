package com.library.repetitory;

import com.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    @Override
    public List<Reader> findAll();

    @Override
    public Optional<Reader> findById(final Long readerId);

    @Override
    public Reader save(final Reader reader);

    @Override
    public void deleteById(final Long readerId);
}
