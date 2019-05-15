package com.desafio.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.demo.model.SalidaDesafio;
import com.desafio.demo.repository.UserRepository;
import com.desafio.test.Desafio;

@Service
public class UserService {
	 @Autowired
	 private UserRepository repository;
	 
	 public SalidaDesafio datos() {
		 
		 Desafio desarrollo= new Desafio();
			
		 SalidaDesafio datos=desarrollo.desarrollo();
		 return datos;
	 }
	 
}
