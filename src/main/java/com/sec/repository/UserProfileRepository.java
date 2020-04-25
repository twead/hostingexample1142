package com.sec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sec.entity.UserProfile;

@Repository
public interface UserProfileRepository  extends CrudRepository<UserProfile, Long>{

}
