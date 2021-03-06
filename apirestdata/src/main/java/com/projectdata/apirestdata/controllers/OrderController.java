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

import com.projectdata.apirestdata.model.Order;
import com.projectdata.apirestdata.model.Payment;
import com.projectdata.apirestdata.repositories.OrderRepository;
import com.projectdata.apirestdata.services.OrderService;

@RestController
@RequestMapping("/orders") // localhost:8080/orders
public class OrderController {
	@Autowired
	private  OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
	
		return ResponseEntity.ok(orderRepository.save(order));
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<Order>>findAll(){
		 
	return ResponseEntity.ok(orderRepository.findAll()); 
	}
	
	//Encontrar pedido pelo id: 
	
	@GetMapping("/{id}") //localhost:8080/orders/{id}
	public Order findById(@PathVariable long id){
		return orderService.findById(id);
		
	}
	
}
