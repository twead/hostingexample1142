package com.sec.service;

import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByUsername(String username);
	
	public  String encryptThisPassword(String password);

	public String userActivation(String code);
	
	//public String updatePassword();
	
}
