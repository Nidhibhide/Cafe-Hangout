package com.jpa.test.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.product;

public interface CategoryRepo extends JpaRepository<product,Integer>{
	List<product> findByCategory(String name);
}
