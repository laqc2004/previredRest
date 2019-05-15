package com.desafio.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SalidaDesafio {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	@JsonProperty("fCreacion")
	private String fCreacion;
	@JsonProperty("fFin")
	private String fFin;
	@JsonProperty("fRecibidas")
	private String fRecibidas;
	@JsonProperty("fFaltantes")
	private String fFaltantes;

	public String getfCreacion() {
		return fCreacion;
	}

	public void setfCreacion(String fCreacion) {
		this.fCreacion = fCreacion;
	}

	public String getfFin() {
		return fFin;
	}

	public void setfFin(String fFin) {
		this.fFin = fFin;
	}

	public String getfRecibidas() {
		return fRecibidas;
	}

	public void setfRecibidas(String fRecibidas) {
		this.fRecibidas = fRecibidas;
	}

	public String getfFaltantes() {
		return fFaltantes;
	}

	public void setfFaltantes(String fFaltantes) {
		this.fFaltantes = fFaltantes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
