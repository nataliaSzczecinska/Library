package com.library.repository;

import java.util.*;
import com.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    public List<Borrow> findAll();
}
