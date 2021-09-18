package com.projectdata.apirestdata.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projectdata.apirestdata.model.User;
import com.projectdata.apirestdata.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private   UserRepository userRepository;

	public User findById(long id) {
		Optional<User> obj =userRepository.findById(id);
		return obj.get();
	}

	

	
	
	
	
	

	
				
	}





