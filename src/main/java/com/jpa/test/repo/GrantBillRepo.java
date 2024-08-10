package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.grantbill;

public interface GrantBillRepo extends JpaRepository<grantbill,Integer> {

}
