package com.sec.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sec.entity.Role;
import com.sec.entity.User;

public class UserDetailsImpl implements UserDetails {

	private User user;
	private UserService userService;
	private BasicTextEncryptor cryptor;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		cryptor = new BasicTextEncryptor();
		cryptor.setPassword("a7%w/42@aD.F2&ad3+!P");
					
		user.setPassword(cryptor.decrypt(user.getPassword()));
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}