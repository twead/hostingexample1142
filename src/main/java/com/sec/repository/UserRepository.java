package com.sec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sec.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {	
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	//@Query(value = "select * from User u inner join userProfile p on u.userProfile = p.id where p.activation = ?1")
	@Query(value = "select * from users inner join user_profile on users.user_profile_id = user_profile.id where user_profile.activation = ?1", nativeQuery = true)
	User findByActivation(String activation);

	@Query(value = "select * from users where email = ?1", nativeQuery = true)
	Optional<User> findUserByEmail(String email);

	@Query(value = "select * from users inner join user_profile on users.user_profile_id = user_profile.id where user_profile.reset_token = ?1", nativeQuery = true)
	Optional<User> findByResetToken(String resetToken);
	
	@Query(value = "select * from users join users_roles on users.id = user_id join roles on roles.id = role_id where role_id=2", nativeQuery = true)
	List<User> findAllProfessionals();
	
	@Query(value = "select * from users inner join user_profile on users.user_profile_id = user_profile.id where users.id = ?1", nativeQuery = true)
	Optional<User> findById(Long id);
	
	@Query(value = "select * from users where username = ?1 and id != ?2", nativeQuery = true)
	User findExistUsernameForUpdate(String username,Long id);
	
	@Query(value = "select * from users where email = ?1 and id != ?2", nativeQuery = true)
	User findExistEmailForUpdate(String email, Long id);
}
