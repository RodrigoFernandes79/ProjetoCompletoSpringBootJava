package com.projectdata.apirestdata.controllers;

import java.util.List;


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

import com.projectdata.apirestdata.model.Product;
import com.projectdata.apirestdata.repositories.ProductRepository;
import com.projectdata.apirestdata.services.ProductService;

@RestController
@RequestMapping("/product") // localhost:8080/product
public class ProductController {
	@Autowired
	private  ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productRepository.save(product));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Product>>findAll(){
		 
	return ResponseEntity.ok(productRepository.findAll()); 
	}
	
	//Encontrar usuario pelo id: 
	
	@GetMapping("/{id}") //localhost:8080/product/{id}
	public Product findById(@PathVariable long id){
		return productService.findById(id);
		
	}
	
}
