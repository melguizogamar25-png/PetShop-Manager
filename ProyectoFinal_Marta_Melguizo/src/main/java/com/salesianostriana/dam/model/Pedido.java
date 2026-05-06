package com.salesianostriana.dam.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Pedido {

	private String codigo;
	private LocalDate fecha;
	private double total;
	private String descripcion;
}
