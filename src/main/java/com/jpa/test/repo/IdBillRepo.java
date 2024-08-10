package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.test.entities.bill;

public interface IdBillRepo extends JpaRepository<bill,Integer> {
	@Query(value = "SELECT max(b.billid) FROM bill b")
    Integer  findMaxbillId();

}
