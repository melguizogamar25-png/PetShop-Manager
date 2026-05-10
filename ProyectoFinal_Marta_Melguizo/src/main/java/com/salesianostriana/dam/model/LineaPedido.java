package com.salesianostriana.dam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class LineaPedido {

	@Id @GeneratedValue
	private Long id;
	
	private int cantidad;
	private double precioUnitario;
	private double subtotal;

	@ManyToOne
	@JoinColumn (name= "pedido_codigo")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn (name= "producto_id")
	private Producto producto;
}
