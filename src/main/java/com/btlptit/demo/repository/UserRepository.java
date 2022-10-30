package com.btlptit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btlptit.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
