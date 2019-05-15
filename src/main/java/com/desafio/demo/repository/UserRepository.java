package com.desafio.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.desafio.demo.model.SalidaDesafio;

public interface UserRepository extends CrudRepository<SalidaDesafio, Long> {

}
