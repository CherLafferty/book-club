package com.cher.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cher.bookclub.models.Book;
import com.cher.bookclub.models.User;
import com.cher.bookclub.services.BookService;
import com.cher.bookclub.services.UserService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String redirectToBooks() {
		return "redirect:/books";
	}

	// Show all Books
	@RequestMapping("/books")
	public String index(Model model, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		} else {
			List<Book> allBooks = bookService.allBooks();
			model.addAttribute("allBooks", allBooks);
			User thisUser = userService.findOne(userId);
			model.addAttribute("userName", thisUser.getUserName());
			User activeUser = userService.findOne(userId);
			model.addAttribute("activeUser", activeUser);
//			System.out.println(activeUser);
			return "home.jsp";
		}

	}
	
	//Render create book
	@GetMapping("/books/new")
	public String create(@ModelAttribute("book") Book book, Model model,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		} else {
			User activeUser = userService.findOne(userId);
			model.addAttribute("activeUser", activeUser);
			System.out.println(activeUser);
			List<Book> allBooks = bookService.allBooks();
			model.addAttribute("allBooks", allBooks);
			return "new.jsp";
		}
	}

	// Create-post a new book
	@PostMapping("/books/new")
	public String createBook(
			@Valid @ModelAttribute("book") Book book,
			BindingResult result,
			Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			if (result.hasErrors()) {
//				List<Book>allBooks = bookService.allBooks();
//				model.addAttribute("allBooks", allBooks);
				return "new.jsp";
			} else {
				User bookUser = userService.findOne(userId);
				book.setBookUser(bookUser);
				bookService.createBook(book);
				return "redirect:/books";
			}
		}
	}

	// Show One
	@RequestMapping("/books/{id}")
	public String showOne(Model model, @PathVariable("id") Long id) {
		Book book = bookService.findBook(id);
		//System.out.println(book);
		model.addAttribute("book", book);

		return "/showOne.jsp";
	}
	
	// render edit page
	@RequestMapping("/books/{id}/edit")
	public String edit(@PathVariable("id")Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "edit.jsp";
	}
	
	//PUT Request action for edit
	@PutMapping("/books/{id}/edit")
	public String update(@Valid @ModelAttribute("book") Book book,
			BindingResult result) {
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			bookService.updateBook(book);
			return "redirect:/books";
		}
	}
	
	//Delete
	@DeleteMapping("/books/{id}/delete")
	public String destroy(@PathVariable("id")Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
	
}
