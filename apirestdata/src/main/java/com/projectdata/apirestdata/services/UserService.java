package com.projectdata.apirestdata.services;


import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.projectdata.apirestdata.exception.ResourceNotFoundException;
import com.projectdata.apirestdata.model.User;
import com.projectdata.apirestdata.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private   UserRepository userRepository;
	
	

	public User findById(long id) throws ResourceNotFoundException {
		Optional<User> obj =userRepository.findById(id);
		obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return obj.get();
	}



	public void delete(long id) throws ResourceNotFoundException {
		 userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		userRepository.deleteById(id);
		
		
	}



	public ResponseEntity<User> updateById(long id, User user) throws ResourceNotFoundException  {
		return userRepository.findById(id)
		 .map(record -> {
             record.setName(user.getName());
             record.setEmail(user.getEmail());
             record.setPhone(user.getPhone());
             User updated = userRepository.save(record);
             return ResponseEntity.ok().body(updated);
         }).orElseThrow(() -> new ResourceNotFoundException(id));
}
	}
	




	
	

	

	
	
	
	
	

	
				
	





