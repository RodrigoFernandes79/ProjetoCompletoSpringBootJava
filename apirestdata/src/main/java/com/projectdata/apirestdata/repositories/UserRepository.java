package com.projectdata.apirestdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectdata.apirestdata.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	

}
