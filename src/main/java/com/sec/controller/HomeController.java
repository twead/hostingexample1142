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
import com.sec.entity.UserProfile;
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
	public String home(){
		return "index";
	}
	
	@RequestMapping("/bloggers")
	public String bloggers(){
		return "bloggers";
	}
	
	@RequestMapping("/stories")
	public String stories(){
		return "stories";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/reg")
    public String registrationForm(@ModelAttribute User user) {
		log.info("Uj user!");
//		log.debug(user.getFullName());
//		log.debug(user.getEmail());
//		log.debug(user.getPassword());
		userService.registerUser(user);
        return "auth/login";
    }
	
	@RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
	public String activation(@PathVariable("code") String code, HttpServletResponse response) {
		userService.userActivation(code);
		return "auth/login";
	}	
	
	@RequestMapping("/forgot")
	public String forgotPassword(Model model){
		model.addAttribute("user", new User());
		return "forgot";
	}
	
	@PostMapping("/resendPWD")
    public String forgotPasswordForm(@ModelAttribute User user) {
		User userForgot = userService.findByEmail(user.getEmail());	
		if(userForgot == null) 
			return "Nincs ilyen emaillel felhasználó!";		
		userService.updatePassword(userForgot);
        return "auth/login";
    }
	
}
