package com.projectdata.apirestdata.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdata.apirestdata.model.User;

@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {

	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1l, "Maria", "mariabrown@gmail.com", "3334-5520","3232" );
		return ResponseEntity.ok().body(u);
	}
}
