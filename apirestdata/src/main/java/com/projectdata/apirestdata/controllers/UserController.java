package com.projectdata.apirestdata.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdata.apirestdata.model.User;
import com.projectdata.apirestdata.repositories.UserRepository;
import com.projectdata.apirestdata.services.UserService;

@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {
	@Autowired
	private  UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userRepository.save(user));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<User>>findAll(){
		 
	return ResponseEntity.ok(userRepository.findAll()); 
	}
	
	//Encontrar usuario pelo id: 
	
	@GetMapping("/{id}") //localhost:8080/users/{id}
	public User findById(@PathVariable long id){
		return userService.findById(id);
		
	}
	
}
