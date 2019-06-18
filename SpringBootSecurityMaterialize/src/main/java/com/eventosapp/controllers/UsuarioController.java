package com.eventosapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.models.Usuario;
import com.eventosapp.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository er;
	
	/**
	 * so redireciona para o forma de cadastro de usuario
	 * @return
	 */
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String form(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>cadastrarUsuario<<<<<<<<<<<<<<<<<<<evento/formEvento");
		return "usuario/formUsuario";
	}
	
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String form(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>cadastrarEvento<<<<<<<<<<<<<<<<<<<cadastrarUsuario");
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";
		}
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		String s = e.encode(usuario.getSenha());
		
		usuario.setSenha(s);
		//er.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso!");
		return "redirect:/cadastrarUsuario";
	}
	
	@RequestMapping("/usuarios")
	public ModelAndView listaUsuarios(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>listaUsuarios<<<<<<<<<<<<<<<<<<<usuario");
		ModelAndView mv = new ModelAndView("usuario/listaUsuarios");
		Iterable<Usuario> usuarios = er.findAll();
		mv.addObject("usuarios", usuarios);
		
		usuarios.forEach(c -> System.out.println(c.getPassword()));
		return mv;
	}
	
	@RequestMapping(value="/detalhesUsuario/{login}", method=RequestMethod.GET)
	public ModelAndView detalhesUsuario(@PathVariable("login") String login){ 
		System.out.println(">>>>>>>>>>>>>>>>>>>>>detalhesUsuario<<<<<<<<<<<<<<<<<<<eventos"); 
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>login: " + login);
		Usuario usuario = er.findByLogin(login);
		ModelAndView mv = new ModelAndView("usuario/detalhesUsuario");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@RequestMapping(value="/editarUsuario", method=RequestMethod.POST)
	public ModelAndView editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>editarUsuario<<<<<<<<<<<<<<<<<<<");
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		
		Usuario oldUsuario = er.findByLogin(usuario.getLogin());
		System.out.println("...............usuario.getLogin: " + oldUsuario.getLogin());
		System.out.println("...............usuario.getSenha: " + oldUsuario.getSenha());
		System.out.println("...............usuario.getPassword: " + oldUsuario.getPassword());
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			ModelAndView mv = new ModelAndView("usuario/detalhesUsuario");
			mv.addObject("usuario", usuario);
			return mv;
		}
		
		String s = e.encode(usuario.getSenha());
		usuario.setSenha(s);
	
		er.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuario alterado com sucesso!");
		ModelAndView mv = new ModelAndView("usuario/listaUsuarios");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping("/deletarUsuario")
	public String deletarUsuario(String login){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>deletarUsuario");
		Usuario usuario = er.findByLogin(login);
		er.delete(usuario);
		return "redirect:/usuarios";
	}
}
