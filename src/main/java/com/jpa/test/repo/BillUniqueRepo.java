package com.jpa.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.bill;


public interface BillUniqueRepo extends JpaRepository<bill,Integer> {
	
	List<bill> findByCustname(String custname);

}
