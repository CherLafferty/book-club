package com.cher.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cher.bookclub.models.Book;
import com.cher.bookclub.models.User;
import com.cher.bookclub.repositories.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	//CRUD
	
	//CREATE
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	
	//READ All
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}
	
	//READ ONE
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	//UPDATE
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}
	
//	public Book updateBookWithUser(Book book, User bookUser) {
//		book.setBookUser(bookUser);
//		return bookRepo.save(book);
//	}
	
	//DELETE
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
	
	
}
