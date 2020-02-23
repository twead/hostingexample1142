package com.sec.repository;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {	
	
	User findByUsername(String username);
	
	User findByActivation(String activation);
	
	User findByEmail(String email);

	User findByFullName(String fullName);
}
