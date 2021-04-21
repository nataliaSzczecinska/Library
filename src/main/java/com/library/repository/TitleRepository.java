package com.library.repository;

import java.util.*;
import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    public List<Title> findAll();
}
