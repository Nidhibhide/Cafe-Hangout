package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.customer;

public interface CustomerRepo extends JpaRepository<customer,Integer> {

}
