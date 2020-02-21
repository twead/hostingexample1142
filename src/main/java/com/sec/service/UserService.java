package com.sec.service;

import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByUsername(String username);
	
	public String encryptingPwd(String password);

	public String userActivation(String code);
	
}
