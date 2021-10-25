package com.lorenzozaar.authuser.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lorenzozaar.authuser.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
	
	User findByUsuarioAndSenha(String usuario, String senha);
	
	User findByUsuario(String usuario);
	
}
