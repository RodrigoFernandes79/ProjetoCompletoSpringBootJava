package com.projectdata.apirestdata.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectdata.apirestdata.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	

}
