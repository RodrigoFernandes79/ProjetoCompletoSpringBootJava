package com.projectdata.apirestdata.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectdata.apirestdata.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
	

}
