package com.sec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sec.entity.Muscle;

@Repository
public interface MuscleRepository extends CrudRepository<Muscle, String>{

	public List<Muscle> findAll();
	
}
