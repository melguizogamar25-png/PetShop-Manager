package com.salesianostriana.dam.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Producto {

	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	private double precio;
	private int stock;
	private LocalDate fechaCaducidad;
	private boolean devolucion;
	
	@Enumerated(EnumType.STRING)
	//Sirve para guardar el enum como String
	private TipoMascota tipoMascota;
	private String imagen;
	private String descripcion;
}
