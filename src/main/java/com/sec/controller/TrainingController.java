package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sec.entity.Muscle;
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
	
	@RequestMapping(path = "/exercise/{muscleurl}", method = RequestMethod.GET)
	public String exercise(@PathVariable("muscleurl") String muscleUrl, Model model) {
		
		String muscleName = muscleService.findMuscleNameByUrl(muscleUrl);
		
		//A muscle neve az exercise másodlagos kulcsa, csak muscle name alapján lehet exerciset keresni, így előtte az url-ből meg kell állapítani a muscle namet.
		model.addAttribute("exercises", exerciseService.findAllByMuscleName(muscleName));
		return "exercise";
	}
	
	@RequestMapping(path = "/exercise/{muscleurl}/{exerciseurl}", method = RequestMethod.GET)
	public String exerciseDetail(@PathVariable("exerciseurl") String exerciseUrl, Model model) {
		
		model.addAttribute("exercises", exerciseService.findByExerciseUrl(exerciseUrl));
		
		return "exerciseDetails";
	}

}
