package com.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repository.RoleRepository;
import com.sec.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private final String USER_ROLE = "USER"; 
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
	}

	@Override
	public void registerUser(User user) {
		Role userRole = roleRepository.findByRole(USER_ROLE);
		
		if(userRole != null) {
			user.getRoles().add(userRole);
		}else {
			user.addRoles(USER_ROLE);
		}
		
		userRepository.save(user);
	}
	
}
