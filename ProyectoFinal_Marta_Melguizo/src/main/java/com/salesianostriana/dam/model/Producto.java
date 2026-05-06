package com.salesianostriana.dam.model;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Producto {

	@Id @GeneratedValue
	private Long id;
	private String nombre;
	private double precio;
	private boolean stock;
	private LocalDate fechaCaducidad;
	private boolean devolucion;
	private TipoMascota tipoMascota;
	private String imagen;
}
