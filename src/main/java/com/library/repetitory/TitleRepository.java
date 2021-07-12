package com.library.repetitory;

import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

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
    public Title save(final Title title);

    @Query
    public List<Title> retrieveBookByTitleFragment(@Param("titleFragment") String titleFragment);

    @Query
    public List<Title> retrieveTitleByAuthor(@Param("author") String author);

    @Query
    public List<Title> retrieveTitleByPublisher(@Param("publisher") String publisher);

    @Query
    public List<Title> retrieveTitleByYear(@Param("year") int year);
}
