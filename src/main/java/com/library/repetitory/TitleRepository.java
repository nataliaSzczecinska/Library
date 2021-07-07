package com.library.repetitory;

import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Transactional
@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    @Override
    public List<Title> findAll();

    @Override
    public Optional<Title> findById(final Long titleId);

    @Override
    public Title save(Title title);

    @Override
    public void deleteById(final Long titleId);
}
