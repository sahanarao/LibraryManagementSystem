package com.libmgmtsys.repository;

import org.springframework.data.repository.CrudRepository;

import com.libmgmtsys.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
}
