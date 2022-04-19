package com.cher.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.cher.bookclub.models.LoginUser;
import com.cher.bookclub.models.User;
import com.cher.bookclub.repositories.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    
    // Register Method
    public User register(User newUser, BindingResult result) {
        //If email already exists in DB
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if (potentialUser.isPresent()) {
    		result.rejectValue("email","registerErrors", "passwords must match");
    	}
        //return null if result has errors
    	if (result.hasErrors()) {
    		return null;
    	} else {
    		//hash and set password and add user to DB
    		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		newUser.setPassword(hashed);
    		//save User to DB
    		return userRepo.save(newUser);
    	}
    }
    
    
    
    //Login Method
    public User login(LoginUser newLoginObject, BindingResult result) {
        //Find the user in DB
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if (!potentialUser.isPresent()) {
    		result.rejectValue("email", "loginError", "invalid email");
    	} else {
    		User user = potentialUser.get();
    		//if BCrypt password does not match--reject
    		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    			//Reject
    			result.rejectValue("password","loginError", "invalid password");
    		}
    		//return null if result has errors
    		if (result.hasErrors() ) {
    			return null;
    		} else {
    			//if return here, user is an object
    			return user;
    		}
    	}
    	return null;   
    }
    
    //Find One
    public User findOne(Long id) {
    	Optional<User> potentialUser = userRepo.findById(id);
    	if (potentialUser.isPresent()) {
    		return potentialUser.get();
    	} else {
    		return null;
    	}
    }
    
    
}
