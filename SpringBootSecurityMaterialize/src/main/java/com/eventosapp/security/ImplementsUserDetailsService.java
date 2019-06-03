package com.eventosapp.security;

import com.eventosapp.repository.UsuarioRepository;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.eventosapp.models.Usuario;


@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>login: " + login);
		Usuario usuario = ur.findByLogin(login);
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}else{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>login: " + usuario.getNomeCompleto());
		}
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
		//return usuario;
	}

}
