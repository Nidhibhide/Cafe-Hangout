package com.jpa.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entities.user;

public interface UserRepo extends JpaRepository<user, Integer> {

}
