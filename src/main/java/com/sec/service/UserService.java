package com.sec.service;

import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByUsername(String username);
	
	public User findByEmail(String username);

	public String userActivation(String code);
	
	public void updatePassword(User user);
	
}
