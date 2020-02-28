package com.sec.service;

import java.util.Optional;

import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user, String fullName);

	public User findByUsername(String username);

	public String userActivation(String code);

	public User findByEmail(String email);
	
	//Elfelejtett jelszóhoz
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findUserByResetToken(String resetToken);
    public void save(User user);

}
