package com.eventosapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ESTA CLASSE INICIA A APLICACAO QUANDO O METODO MAIN E EXECUTADO
 * POR DEFAULT, ELA EXECUTA O TOMCAT
 * 
 * NAO PRECISA EXTENDER NENHUMA OUTRA CLASSE
 * MAS PARA USAR O JBOSS CONTAINER, PRECISAR FAZER ALTERACAO EXTENDENDO SpringBootServletInitializer
 * E IMPLEMENTANDO O METODO configure
 * @author aniziomaia
 *
 */
@SpringBootApplication
public class EventosappApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EventosappApplication.class, args);
		//System.out.print(new BCryptPasswordEncoder().encode("123"));
		System.out.print("##:SENHA CRIPTOGRAFADA:## " + new BCryptPasswordEncoder().encode("root")); 
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return super.configure(builder);
	}
}
