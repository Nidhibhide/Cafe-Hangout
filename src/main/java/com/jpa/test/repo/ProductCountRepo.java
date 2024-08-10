package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.test.entities.product;

public interface ProductCountRepo extends JpaRepository<product,Integer>{
	
	@Query(value = "SELECT COUNT(c) FROM product c")
	Integer ProductCount();



}
