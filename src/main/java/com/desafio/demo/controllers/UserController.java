package com.desafio.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.demo.model.SalidaDesafio;
import com.desafio.demo.service.UserService;
import com.desafio.test.Desafio;

@RestController
public class UserController {
	
	    @RequestMapping("/")
	    @ResponseBody
	    public String home() {
	        return "Hello World!";
	    }
	
	
	 @RequestMapping(value = "/resultado", method = RequestMethod.GET)
	 public SalidaDesafio datosDesafio() {
	     System.out.println("este es un test");
	     Desafio test=new Desafio();
		 SalidaDesafio datos=test.desarrollo();
		 return datos;
	 }
	
	
}
