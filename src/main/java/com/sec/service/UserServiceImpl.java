package com.sec.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.entity.UserProfile;
import com.sec.repository.RoleRepository;
import com.sec.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private EmailService emailService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final String USER_ROLE = "USER"; 
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}	

	@Override
	public User findByEmail(String email) {	
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());
		
		if(userCheck != null) 
			return "Already exist!";
		
		Role userRole = roleRepository.findByRole(USER_ROLE);
		
		if(userRole != null) {
			userToRegister.getRoles().add(userRole);
		}else {
			userToRegister.addRoles(USER_ROLE);
		}
		
		userToRegister.setActivation(generatedKey());
		userToRegister.setEnabled(false);
		userToRegister.setPassword(bCryptPasswordEncoder.encode(userToRegister.getPassword()));
		userRepository.save(userToRegister);
		emailService.sendMessage(userToRegister.getEmail(),userToRegister.getActivation(),userToRegister.getUserProfile().getFullName());
		
		return "ok";
	}

	private String generatedKey() {
		Random random = new Random();
		char[] code = new char[16];
		for(int i = 0; i < code.length; i++) {
			code[i] = (char) ('a' + random.nextInt(26));
		}
		return new String(code);
	}

	@Override
	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if(user == null)
			return "noresult";
		
		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		return "ok";
	}

	@Override
	public void updatePassword(User userForgot) {
		userForgot.setResetToken(generatedKey());
		emailService.sendNewPasswordRequest(userForgot.getEmail(), userForgot.getUserProfile().getFullName(), userForgot.getResetToken());
		userForgot.setPassword(bCryptPasswordEncoder.encode(userForgot.getResetToken()));
		userForgot.setResetToken("");
		userRepository.save(userForgot);
	}
	
}
