package com.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Muscle;
import com.sec.repository.MuscleRepository;

@Service
public class MuscleServiceImpl implements MuscleService {

	MuscleRepository muscleRepository;
	
	@Autowired
	public MuscleServiceImpl(MuscleRepository muscleRepository) {
		this.muscleRepository = muscleRepository;
	}

	@Override
	public List<Muscle> findAll() {
		return muscleRepository.findAll();
	}

}
