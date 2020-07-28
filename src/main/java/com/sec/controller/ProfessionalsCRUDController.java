package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sec.entity.User;
import com.sec.service.UserService;

@Controller
@RequestMapping("/professionals")
public class ProfessionalsCRUDController {

	private final String USER_ROLE = "PROFESSIONAL";
	
	private UserService userService;
	
	@Autowired
    public ProfessionalsCRUDController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/list")
    public String getAllProducts(Model model) {
        model.addAttribute("professionals", userService.findAllProfessionals());
        return "display-professionals";
    }

    @RequestMapping("/showform")
    public String createProduct(Model model) {
        model.addAttribute("professional", new User());
        return "add-professional";
    }

    @PostMapping("/add")
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
    @GetMapping("/add")
    public String returnToSaveProduct(@ModelAttribute User professional, Model model) {   	
        return "redirect:/professionals/showform";
    }
    

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") Long id) {    	
        model.addAttribute("professional", userService.findById(id));
        
        return "update-professional";
    }
    
    @PostMapping("/update/{id}")
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
    
    //hiba bekövetkeztekor hibaoldal kiküszöbölése
   /* @RequestMapping(path = "/professionals/update/{id}",  method = RequestMethod.GET)
    public String returnToSaveProduct(@PathVariable("id") long id, Model model) {
    	model.addAttribute("existedUsername", "existed username");
    	
        return "redirect:/professionals/edit/{id}";
    }*/
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id, Model model) {
    	User professional = this.userService.findById(id);
    	
		this.userService.delete(professional);
        return "redirect:/professionals/list";
    }
    
    
}