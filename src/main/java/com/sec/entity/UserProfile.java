package com.sec.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

	@GeneratedValue
	@Id
	private Long id;
	
    @OneToOne(mappedBy = "userProfile")
    private User user;

	@Column(length = 64)
	private String fullName;
    
	public UserProfile() {

    }

	public User getUser() {
		return user;
	}


	public UserProfile(User user, String fullName) {
		super();
		this.user = user;
		this.fullName = fullName;
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
	
}
