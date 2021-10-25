package com.lorenzozaar.authuser.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lorenzozaar.authuser.models.User;
import com.lorenzozaar.authuser.models.md5Factory;
import com.lorenzozaar.authuser.repos.UserRepo;

@Controller
public class UserController {

	@Autowired
	private UserRepo repo;
	
	
	@PostMapping("/")
	public String validateLogin(@ModelAttribute(name = "formUser") @Valid User user, Model model) {
		User queryUser = repo.findByUsuarioAndSenha(user.getUsuario(), md5Factory.mdfy(user.getSenha()));
		if (queryUser != null) {
			model.addAttribute("id", queryUser.getId());
			model.addAttribute("user", queryUser.getUsuario());
			model.addAttribute("senha", queryUser.getSenha());
			
			return "flappy";
		}else {
			
			return "index";
		}
		
	}
	
	@PostMapping("/cadastro")
	public String register(@ModelAttribute(name = "formUser") @Valid User user, Model model) {
		User queryUser = repo.findByUsuario(user.getUsuario());
		if (queryUser != null) {
			model.addAttribute("error","Nome de usuario jão utilizado");
			System.out.println(queryUser);
			return "cadastro";
		}else {
			user.setSenha(md5Factory.mdfy(user.getSenha()));
			
			repo.save(user);
			
			return "redirect:index";
		}
		
	}
	

//	public void deleteUser(@RequestBody @Valid User user) {
//		repo.deleteById(user.getId());
//	}
//
//	public User alterUser(@RequestBody @Valid User user) {
//		User queryUser = repo.findByUsuarioAndSenha(user.getUsuario(), md5Factory.mdfy(user.getSenha()));
//
//		if (queryUser != null) {
//			queryUser.setSenha(user.getSenha());
//			repo.save(queryUser);
//			return queryUser;
//		} else {
//			return null;
//		}
//
//	}	
//	
//	@GetMapping("/")
//	public ModelAndView logar() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("index");
//		return mv;
//	}
//	
//	

}
