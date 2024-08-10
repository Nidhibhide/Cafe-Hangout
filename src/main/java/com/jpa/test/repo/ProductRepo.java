package com.jpa.test.repo;

import org.springframework.data.repository.CrudRepository;

import com.jpa.test.entities.product;

public interface ProductRepo extends CrudRepository<product,Integer>{

}
