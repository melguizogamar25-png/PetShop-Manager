package com.salesianostriana.dam.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Cliente {

	@Id @GeneratedValue
	private Long id;
	private String nombre;
	private String email;
	private int telefono;
	private boolean socioTienda;
	
}
