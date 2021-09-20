package com.projectdata.apirestdata.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.projectdata.apirestdata.model.Category;
import com.projectdata.apirestdata.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private   CategoryRepository categoryRepository;

	public Category findById(long id) {
		Optional<Category> obj =categoryRepository.findById(id);
		return obj.get();
	}

	

	
	
	
	
	

	
				
	}





