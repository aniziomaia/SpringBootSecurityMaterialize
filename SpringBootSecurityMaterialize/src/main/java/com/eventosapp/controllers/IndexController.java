package com.eventosapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/eventosapp")
public class IndexController {

	@RequestMapping("/")
	public String index(){
		System.out.println(">>>>>>>>>>1>>>>>>>>>>IndexController.index");
		return "index";
	}
	
	@RequestMapping(method =RequestMethod.GET, path = "/entrar")
	public String entrar(){
		System.out.println(">>>>>>>>>>>1>>>>>>>>>IndexController.entrar");
		return "entrar"; 
	}
	
	/**
	 * lougut via metodo de acao
	 * exemplo de chamada: <a href="/logoutMetodo"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logoutMetodo", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("################ LOGUT #################");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			request.getSession().invalidate();
		}
		return "redirect:/";
	}
	
}
