package com.projectdata.apirestdata.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectdata.apirestdata.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
	

}
