package com.projectdata.apirestdata.services;


import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.projectdata.apirestdata.model.Product;
import com.projectdata.apirestdata.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private   ProductRepository productRepository;

	public Product findById(long id) {
		Optional<Product> obj =productRepository.findById(id);
		return obj.get();
	}

	

	
	
	
	
	

	
				
	}





