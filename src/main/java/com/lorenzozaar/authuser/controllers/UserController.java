package com.lorenzozaar.authuser.controllers;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lorenzozaar.authuser.models.User;
import com.lorenzozaar.authuser.models.md5Factory;
import com.lorenzozaar.authuser.repos.UserRepo;

@Controller
public class UserController {

	@Autowired
	private UserRepo repo;
	public int logado;

	public int validatieLog(int logado) {
		return logado = 1;
	}

	@PostMapping("/")
	public String validateLogin(@ModelAttribute(name = "formUser") @Valid User user, Model model) {
		User queryUser = repo.findByUsuarioAndSenha(user.getUsuario(), md5Factory.mdfy(user.getSenha()));
		if (queryUser != null) {
			model.addAttribute("id", queryUser.getId());
			model.addAttribute("user", queryUser.getUsuario());
			model.addAttribute("senha", queryUser.getSenha());

			return "flappy";
		} else {

			return "index";
		}

	}

	@PostMapping("/cadastro")
	public String register(@ModelAttribute(name = "formUser") @Valid User user, Model model) {
		System.out.println(user);

		User queryUser = repo.findByUsuario(user.getUsuario());
		if (queryUser == null) {
			String senha = user.getSenha();
			String senha2 = user.getSenha2();
			
			System.out.println(senha + "||" + senha2 );
			if (senha.equals(senha2)) {
				
				System.out.println(user.getSenha());
				System.out.println(user.getSenha2());
				user.setSenha(md5Factory.mdfy(user.getSenha()));
				repo.save(user);
				return "index";
			}
		}
		return "cadastro";

	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		Optional<User> queryUser = repo.findById(id);
		
		repo.delete(queryUser.get());
		
		return "index";
	}

	@PutMapping("/update")
	public String updateUser(@Valid User user) {
		String senha = md5Factory.mdfy(user.getSenha());
		user.setSenha(senha);		
		System.out.println(user);
		
		repo.save(user);
		
		return "flappy";
	}
	

}
