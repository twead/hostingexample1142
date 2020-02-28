package com.sec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sec.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {	
	
	User findByUsername(String username);
	
	//@Query(value = "select * from User u inner join u.userProfile p on u.userProfile = p.id where p.activation = ?1")
	@Query(value = "select * from users inner join user_profile on users.user_profile_id = user_profile.id where user_profile.activation = ?1", nativeQuery = true)
	User findByActivation(String activation);
	
	@Query(value = "select * from users inner join user_profile on users.user_profile_id = user_profile.id where user_profile.email = ?1", nativeQuery = true)
	User findByEmail(String email);

}
