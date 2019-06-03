package com.eventosapp.controllers;

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
	
}
