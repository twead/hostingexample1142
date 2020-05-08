package com.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Exercise;
import com.sec.entity.Muscle;
import com.sec.repository.ExerciseRepository;
import com.sec.repository.MuscleRepository;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	ExerciseRepository exerciseRepository;
	
	@Autowired
	public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public List<Exercise> findAllByMuscleName(String name) {
		return exerciseRepository.findAllByMuscleName(name);
	}

}
