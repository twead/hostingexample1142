package com.sec.service;

import java.util.Optional;

import com.sec.entity.User;

public interface UserService {

	public User findByUsername(String username);

	public User findByEmail(String email);

	public String registerUser(User user, String fullName);

	public String userActivation(String code);

	// Elfelejtett jelszóhoz
	public Optional<User> findUserByEmail(String email);

	public Optional<User> findUserByResetToken(String resetToken);

	public void save(User user);

}
