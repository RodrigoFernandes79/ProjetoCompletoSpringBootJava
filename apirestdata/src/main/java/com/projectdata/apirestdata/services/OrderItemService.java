package com.projectdata.apirestdata.services;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.projectdata.apirestdata.model.Order;
import com.projectdata.apirestdata.repositories.OrderItemRepository;
import com.projectdata.apirestdata.repositories.OrderRepository;


@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;

	

}
