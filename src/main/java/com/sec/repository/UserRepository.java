package com.sec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sec.entity.UserProfile;

import com.sec.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {	
	
	User findByUsername(String username);
	
	User findByActivation(String activation);
	
	User findByEmail(String email);

	@Query(value = "select u from User u inner join u.userProfile p on u.userProfile = p.id where p.fullName = ?1")
	User findByProfileFullname(String fullName);
}
