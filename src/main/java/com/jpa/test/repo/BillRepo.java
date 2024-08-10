package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.bill;

public interface BillRepo extends JpaRepository<bill, Integer> {

}
