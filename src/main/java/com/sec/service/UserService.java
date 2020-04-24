package com.sec.service;

import java.util.List;
import java.util.Optional;

import com.sec.entity.User;


public interface UserService {

	public User findByUsername(String username);

	public User findByEmail(String email);
	
	public User findByActivation(String code);

	public String registerUser(User user, String fullName);

	public String userActivation(String code);

	// Elfelejtett jelszóhoz
	public Optional<User> findUserByEmail(String email);

	public Optional<User> findUserByResetToken(String resetToken);

	public void save(User user);
	
	public List<User> findAllProfessionals();
	
	public User findById(Long id);

	public void delete(User professional);

}
