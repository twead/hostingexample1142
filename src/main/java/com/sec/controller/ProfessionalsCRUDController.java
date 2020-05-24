package com.sec.controller;

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

@Controller
public class ProfessionalsCRUDController {

	private final String USER_ROLE = "PROFESSIONAL";
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
    @RequestMapping(path = {"/professionals/list"}, method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("professionals", userService.findAllProfessionals());
        return "display-professionals";
    }

    @RequestMapping("/professionals/showform")
    public String createProduct(Model model) {
        model.addAttribute("professional", new User());
        return "add-professional";
    }

    @PostMapping("/professionals/add")
    public String saveProduct(@ModelAttribute User professional, Model model) {
    	
    	String existedUsername = professional.getUsername();
		if (userService.findByUsername(existedUsername) != null) {
			model.addAttribute("existedUsername", existedUsername);
			return "forward:showform";
		}

		String existedEmail = professional.getEmail();
		if (userService.findByEmail(existedEmail) != null) {
			model.addAttribute("existedEmail", existedEmail);
			return "forward:showform";
		}
    	
    	userService.registerUser(professional, USER_ROLE);
    	
    	userService.save(professional);
        return "redirect:list";
    }
    
    //Nyelv váltásakor hibaablak kiküszöbölése
    @RequestMapping(path = "/professionals/add",  method = RequestMethod.GET)
    public String returnToSaveProduct(@ModelAttribute User professional, Model model) {   	
        return "redirect:/professionals/showform";
    }

    @RequestMapping(path = "/professionals/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") Long id) {    	
        model.addAttribute("professional", userService.findById(id));
        
        return "update-professional";
    }
    
    @PostMapping("/professionals/update/{id}")
	public String updateProfessional(@PathVariable("id") long id, @ModelAttribute User professional, Model model) {
		
    	if(userService.findExistUsernameForUpdate(professional.getUsername(),id) != null) {
    		model.addAttribute("existedUsername", "existed username");
			return "redirect:/professionals/update/{id}";
    	}
    	
    	
    	if(userService.findExistEmailForUpdate(professional.getEmail(),id) != null) {
    		model.addAttribute("existedEmail", "existed email");
			return "redirect:/professionals/update/{id}";
    	}
    	
    	professional.getUserProfile().setId(id);
    	userService.updateUser(professional, USER_ROLE);
    	
		model.addAttribute("professionals", this.userService.findAllProfessionals());
		return "display-professionals";	
		
	}
    
    //Nyelv váltásakor hibaablak kiküszöbölése
    @RequestMapping(path = "/professionals/update/{id}",  method = RequestMethod.GET)
    public String returnToSaveProduct(@PathVariable("id") long id, @ModelAttribute User professional, Model model) {
    	model.addAttribute("existedUsername", "existed username");
    	
        return "redirect:/professionals/edit/{id}";
    }
    
    @RequestMapping(path = "/professionals/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") Long id, Model model) {
    	User professional = this.userService.findById(id);
    	
		this.userService.delete(professional);
        return "redirect:/professionals/list";
    }
    
    
}