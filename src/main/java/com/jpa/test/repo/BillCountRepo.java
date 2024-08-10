package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.test.entities.grantbill;

public interface BillCountRepo extends JpaRepository<grantbill, Integer> {
	@Query(value = "SELECT COUNT(c) FROM grantbill c")
    Integer CountBill();


}
