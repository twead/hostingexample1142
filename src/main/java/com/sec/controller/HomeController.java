package com.sec.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sec.entity.User;
import com.sec.service.UserService;
import com.sec.service.UserServiceImpl;

@Controller
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private UserService userService;

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/reg")
	public String registrationForm(@ModelAttribute User user, Model model) {

		String existedUsername = user.getUsername();
		if (userService.findByUsername(existedUsername) != null) {
			model.addAttribute("existedUsername", existedUsername);
			return "registration";
		}

		String existedEmail = user.getEmail();
		if (userService.findByEmail(existedEmail) != null) {
			model.addAttribute("existedEmail", existedEmail);
			return "registration";
		}

		log.info("Uj user!");
//		log.debug(user.getFullName());
//		log.debug(user.getEmail());
//		log.debug(user.getPassword());
		String fullName = user.getUserProfile().getFullName();
		userService.registerUser(user, fullName);
		model.addAttribute("registrationSuccessful", "Registration Successful");
		return "auth/login";
	}

	@RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
	public String activation(@PathVariable("code") String code, HttpServletResponse response, Model model) {
		
		userService.userActivation(code);
		model.addAttribute("activationSuccessful", "Activation Successful");
		return "auth/login";
	}

}
