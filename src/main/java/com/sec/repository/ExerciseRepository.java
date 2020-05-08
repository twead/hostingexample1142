package com.sec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sec.entity.Exercise;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, String>{

	@Query(value = "select * from exercises where muscle_muscle_name = ?1", nativeQuery = true)
	public List<Exercise> findAllByMuscleName(String name);
	
	@Query(value = "select * from exercises where exercise_name = ?1", nativeQuery = true)
	public List<Exercise> findAllByExerciseName(String name);
}
