package com.sec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@GeneratedValue
	@Id
	private Long id;

	@Column(length = 64)
	private String fullName;

	@Column(length = 20)
	private String activation;

	@Column(length = 64)
	private String address;

	@Column(length = 32)
	private String phoneNumber;

	@Column(length = 60)
	private String resetToken;

	@Column(unique = true, nullable = false)
	@Email
	private String email;

	@OneToOne(mappedBy = "userProfile")
	private User user;

	public UserProfile() {

	}

	public UserProfile(User user, String fullName, String activation, String address, String phoneNumber,
			String resetToken, String email) {
		super();
		this.user = user;
		this.fullName = fullName;
		this.activation = activation;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.resetToken = resetToken;
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
