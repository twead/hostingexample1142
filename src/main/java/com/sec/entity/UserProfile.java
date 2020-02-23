package com.sec.entity;

import java.io.Serializable;
import java.util.Set;

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
	
    @OneToOne()
    private User user;
    
	public UserProfile() {

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
	
}
