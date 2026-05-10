package com.salesianostriana.dam.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Pedido {

	@Id
	private String codigo;
	
	private LocalDate fecha;
	private double total;
	private String descripcion;
	
	/*Preguntar a Ángel si poner un enum con el 
	 * estado (pendiente, enviado, entregado)
	 */
	
	@ManyToOne //Un pedido solo tiene un cliente
	@JoinColumn (name= "cliente_id")
	private Cliente cliente;
	
	@OneToMany //Un pedido tiene unas cuantas de lineas de pedido
	private List <LineaPedido> lineas = new ArrayList<>(); 
}
