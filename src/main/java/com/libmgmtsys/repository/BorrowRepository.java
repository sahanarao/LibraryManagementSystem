package com.libmgmtsys.repository;

import java.util.List;

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

    @Query(value = "select b.borrow_id,b.book_id,b.booking_date,b.quantity from Borrow b where b.user_id = ?1")
	List<Object[]> findByUSerId(String user_id);
}
