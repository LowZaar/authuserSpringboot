package com.lorenzozaar.authuser.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lorenzozaar.authuser.models.User;

@Controller
public class viewController {
	

	@GetMapping("/flappy")
	public String getGame() {
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String getindex(Model model) {
		model.addAttribute("formUser", new User());
		
		return "index";
	}

	@GetMapping("/cadastro")
	public String newUser(Model model) {
		
		model.addAttribute("formUser", new User());
		
		return "cadastro";
	}
	
	@GetMapping("/update")
	public String updatePass(Model model) {
		model.addAttribute("Pwdform", new User());
		
		return "flappy";
	}
	
}
