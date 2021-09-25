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

import com.projectdata.apirestdata.model.OrderItem;
import com.projectdata.apirestdata.repositories.OrderItemRepository;
import com.projectdata.apirestdata.services.OrderItemService;

@RestController
@RequestMapping("/orderItem") // localhost:8080/orderItem
public class OrderItemController {
	@Autowired
	private  OrderItemService orderItemService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
		return ResponseEntity.ok(orderItemRepository.save(orderItem));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<OrderItem>>findAll(){
		 
	return ResponseEntity.ok(orderItemRepository.findAll()); 
	}
	
	
	
}
