package com.library.repository;

import java.util.*;
import com.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    public List<BookCopy> findAll();
}
