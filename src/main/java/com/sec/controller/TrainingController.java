package com.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrainingController {

	@RequestMapping("/exercise")
	public String exercise() {
		return "exercise";
	}

}
