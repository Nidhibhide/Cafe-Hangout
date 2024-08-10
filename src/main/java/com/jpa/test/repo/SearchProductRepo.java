package com.jpa.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.product;

public interface SearchProductRepo extends JpaRepository<product,Integer> {

	List<product> findByProductName(String productName);
}
