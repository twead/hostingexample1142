package com.sec.service;

import com.sec.entity.User;

public interface UserService {
	
	public void registerUser(User user);

	public User findByUsername(String username);
	
}
