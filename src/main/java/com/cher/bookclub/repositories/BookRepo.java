package com.cher.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cher.bookclub.models.Book;

public interface BookRepo extends CrudRepository<Book, Long> {
	List<Book> findAll();

}
