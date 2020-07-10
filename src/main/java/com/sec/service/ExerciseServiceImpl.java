package com.sec.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Exercise;
import com.sec.repository.ExerciseRepository;

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

	@Override
	public Exercise findByExerciseUrl(String url) {
		return exerciseRepository.findByExerciseUrl(url);
	}
	
	@PostConstruct
	public void transformToUrl() {

		for(Exercise e: exerciseRepository.findAll()) {
			String url = e.getExerciseName().toLowerCase()
					.replace(" ", "-")
					.replace("á", "a")
					.replace("í", "i")
					.replace('é','e')
					.replace('ű','u')
					.replace('ú','u')
					.replace('ü','u')
					.replace('ö','o')
					.replace('ó','o')
					.replace('ő','o');
			e.setUrl(url);
			exerciseRepository.save(e);
		}
		
	}
	
}
