package com.sec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repository.RoleRepository;
import com.sec.service.UserService;

@Controller
public class ProfessionalsCRUDController {

	private final String USER_ROLE = "PROFESSIONAL";
	
	private UserService userService;	
	private RoleRepository roleRepository;	
	
	@Autowired	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}


	@RequestMapping("/professionals/list")
	public String index(User professional) {
		return "display-professionals";
	}
	
	
	@GetMapping("/professionals/showForm")
	public String showProfessionalForm(User professional) {
		return "add-professional";
	}
	
	@GetMapping("/professionals/list")
	public String professionals(Model model) {
		model.addAttribute("professionals", this.userService.findAllProfessionals());
		return "display-professionals";
	}
	
	@PostMapping("/professionals/add")
	public String addprofessional(@Valid User professional, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-professional";
		}
		
		Role userRole = roleRepository.findByRole(USER_ROLE);

		if (userRole != null) {
			professional.getRoles().add(userRole);
		} else {
			professional.addRoles(USER_ROLE);
		}

		
		this.userService.save(professional);
		return "redirect:list";
	}
	
	
	@GetMapping("/professionals/edit/{id}")
	public String showUpdateForm(@PathVariable ("id") long id, Model model) {
		User professional = this.userService.findById(id);
				//.orElseThrow(() -> new IllegalArgumentException("Invalid professional id : " + id));
		
		model.addAttribute("professional", professional);
		return "update-professional";
	}
	
	@PostMapping("/professionals/update/{id}")
	public String updateProfessional(@PathVariable("id") long id, @Valid User professional, BindingResult result, Model model) {
		if(result.hasErrors()) {
			professional.setId(id);
			return "update-professional";
		}
		
		// update student
		userService.save(professional);
		
		// get all professionals ( with update)
		model.addAttribute("professionals", this.userService.findAllProfessionals());
		return "display-professionals";
	}
	
	@GetMapping("/professionals/delete/{id}")
	public String deleteProfessional(@PathVariable ("id") long id, Model model) {
		
		User professional = this.userService.findById(id);
				//.orElseThrow(() -> new IllegalArgumentException("Invalid professional id : " + id));
		
		this.userService.delete(professional);
		model.addAttribute("professionals", this.userService.findAllProfessionals());
		return "display-professionals";
		
	}
}