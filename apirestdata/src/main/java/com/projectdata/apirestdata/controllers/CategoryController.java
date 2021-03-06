package com.projectdata.apirestdata.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projectdata.apirestdata.model.Category;
import com.projectdata.apirestdata.repositories.CategoryRepository;
import com.projectdata.apirestdata.services.CategoryService;

@RestController
@RequestMapping("/categories") // localhost:8080/categories
public class CategoryController {
	@Autowired
	private  CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryRepository.save(category));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Category>>findAll(){
		 
	return ResponseEntity.ok(categoryRepository.findAll()); 
	}
	
	//Encontrar usuario pelo id: 
	
	@GetMapping("/{id}") //localhost:8080/categories/{id}
	public Category findById(@PathVariable long id){
		return categoryService.findById(id);
		
	}
	
}
