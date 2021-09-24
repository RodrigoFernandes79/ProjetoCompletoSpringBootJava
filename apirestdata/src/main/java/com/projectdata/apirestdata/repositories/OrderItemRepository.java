package com.projectdata.apirestdata.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectdata.apirestdata.model.Order;
import com.projectdata.apirestdata.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	
	

}
