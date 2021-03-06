package com.projectdata.apirestdata.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projectdata.apirestdata.exception.ResourceNotFoundException;
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
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userRepository.save(user));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<User>>findAll(){
		 
	return ResponseEntity.ok(userRepository.findAll()); 
	}
	
	//Encontrar usuario pelo id: 
	
	@GetMapping("/{id}") //localhost:8080/users/{id}
	public User findById(@PathVariable long id) throws ResourceNotFoundException {
		return userService.findById(id);
		
	}
	
	@DeleteMapping("/{id}") // localhost:8080/users/{id}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable long id) throws ResourceNotFoundException {
		 userService.delete(id);
	}
	
	@PutMapping("/{id}") // localhost:8080/users/{id}
	public ResponseEntity<User> updateById(@PathVariable long id , @RequestBody User user) throws ResourceNotFoundException{
		return userService.updateById(id, user);
		
	}
}
