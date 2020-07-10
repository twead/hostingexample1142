package com.sec.service;

import java.util.List;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void transformToUrl() {
		
		for(Muscle m: muscleRepository.findAll()) {
			String url = m.getMuscleName().toLowerCase()
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
			m.setUrl(url);
			muscleRepository.save(m);
		}
	}

	@Override
	public String findMuscleNameByUrl(String url) {	
		return muscleRepository.findMuscleNameByUrl(url);
	}
}
