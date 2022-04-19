package com.cher.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cher.bookclub.models.LoginUser;
import com.cher.bookclub.models.User;
import com.cher.bookclub.services.UserService;



@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "index.jsp";
	}
	
	//Register new User
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model, HttpSession session) {
		//have Register connect to Service
		userService.register(newUser,  result);
		//check for errors
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/books";
		}
	}
	
	//Login User
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result, Model model, HttpSession session) {
		User user = userService.login(newLogin,  result);
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {
			session.setAttribute("user_id", user.getId());
			return "redirect:/books";
		}
	}
	
	//Home Route
//	@RequestMapping("/home")
//	public String home(HttpSession session, Model model) {
//		//get User from session
//		Long userId = (Long) session.getAttribute("user_id");
//		//check if userId is null
//		if (userId == null ) {
//			
//			return "redirect:/";
//		} else {
//			System.out.println(userId);
//			//go to DB an get user that matches session user
//			User thisUser = userService.findOne(userId);
////			model.addAttribute("thisUser", thisUser);
//			model.addAttribute("userName", thisUser.getUserName());
//			return "home.jsp";
//		}
//	}
//	
	//Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}

