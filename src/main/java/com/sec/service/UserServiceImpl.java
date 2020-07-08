package com.sec.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repository.RoleRepository;
import com.sec.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private EmailServiceImpl emailService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, EmailServiceImpl emailService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.emailService = emailService;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findByActivation(String code) {
		return userRepository.findByActivation(code);
	}

	@Override
	public String registerUser(User userToRegister, String role) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());

		if (userCheck != null)
			return "Already exist!";

		Role userRole = roleRepository.findByRole(role);

		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(role);
		}

		userToRegister.getUserProfile().setActivation(generatedKey());
		userToRegister.setEnabled(false);
		userToRegister.setPassword(bCryptPasswordEncoder.encode(userToRegister.getPassword()));
		if (userToRegister.getUserProfile().getFullName() == null)
			userToRegister.getUserProfile().setFullName(userToRegister.getUsername());
		userRepository.save(userToRegister);	
		

		if(userRole.getRole().equals("PROFESSIONAL")) {
			userToRegister.getRoles().add(roleRepository.findByRole("USER"));
		}
		userRepository.save(userToRegister);

		emailService.sendActivationMessage(userToRegister);

		return "ok";
	}

	@Override
	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null)
			return "noresult";

		user.setEnabled(true);
		user.getUserProfile().setActivation("");
		userRepository.save(user);
		return "ok";
	}

	@Override
	public Optional findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public Optional findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new UserDetailsImpl(user);
	}

	private String generatedKey() {
		Random random = new Random();
		char[] code = new char[16];
		for (int i = 0; i < code.length; i++) {
			code[i] = (char) ('a' + random.nextInt(26));
		}
		return new String(code);
	}

	@Override
	public List<User> findAllProfessionals() {
		return userRepository.findAllProfessionals();
	}

	@Override
	public User findById(Long id) {
		User user = userRepository.findById(id).get();;
		return user;
	}

	@Override
	public void delete(User professional) {
		userRepository.delete(professional);
	}

	@Override
	public User findExistUsernameForUpdate(String username, Long id) {
		return userRepository.findExistUsernameForUpdate(username, id);
	}

	@Override
	public User findExistEmailForUpdate(String email, Long id) {
		return userRepository.findExistEmailForUpdate(email, id);
	}
	
	@Override
	public void updateUser(User user, String role) {
		
		Role userRole = roleRepository.findByRole(role);

		if (userRole != null) {
			user.getRoles().add(userRole);
		} else {
			user.addRoles(role);
		}
		
		userRepository.save(user);
	}

}
