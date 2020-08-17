package com.libmgmtsys.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.libmgmtsys.model.Borrow;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from Borrow c where c.borrow_id = ?1")
    void deleteByBorrowerId(String orderId);
}
