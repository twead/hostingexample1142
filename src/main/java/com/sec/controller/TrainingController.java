package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sec.service.MuscleService;

@Controller
public class TrainingController {
	
	private MuscleService muscleService;
	
	@Autowired
	public void setMuscleService(MuscleService muscleService) {
		this.muscleService = muscleService;
	}

	@RequestMapping("/exercise")
	public String exercise(Model model) {
		model.addAttribute("muscles", muscleService.findAll());
		return "exercise";
	}
	
	

}
