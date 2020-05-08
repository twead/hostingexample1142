package com.sec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sec.entity.Exercise;

@Service
public interface ExerciseService {

	public List<Exercise> findAllByMuscleName(String name);
	
}
