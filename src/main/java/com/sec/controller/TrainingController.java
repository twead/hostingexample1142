package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sec.entity.Exercise;
import com.sec.repository.ExerciseRepository;
import com.sec.service.ExerciseService;
import com.sec.service.MuscleService;

@Controller
public class TrainingController {
	
	private MuscleService muscleService;
	private ExerciseService exerciseService;

	@Autowired
	public void setMuscleService(MuscleService muscleService) {
		this.muscleService = muscleService;
	}

	@Autowired
	public void setExerciseService(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@RequestMapping("/exercise")
	public String muscle(Model model) {
		model.addAttribute("muscles", muscleService.findAll());
		return "muscle";
	}
	
	@RequestMapping(path = "/exercise/{name}", method = RequestMethod.GET)
	public String exercise(@PathVariable("name") String name, Model model) {
		
		model.addAttribute("exercises", exerciseService.findAllByMuscleName(name));
		return "exercise";
	}
	
	@RequestMapping(path = "/exercise/{musclename}/{exercisename}", method = RequestMethod.GET)
	public String exerciseDetail(@PathVariable("exercisename") String name, Model model) {
		
		model.addAttribute("exercises", exerciseService.findAllByMuscleName(name));
		return "exerciseDetails";
	}

}
