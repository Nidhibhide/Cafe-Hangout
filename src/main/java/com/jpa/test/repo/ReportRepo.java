package com.jpa.test.repo;

import org.springframework.data.repository.CrudRepository;

import com.jpa.test.entities.grantbill;

public interface ReportRepo extends CrudRepository<grantbill,Integer> {
    

}
