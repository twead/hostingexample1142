package com.sec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sec.entity.Muscle;

@Service
public interface MuscleService {

	public List<Muscle> findAll();
	
}
