package com.salesianostriana.dam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class LineaPedido {

	@Id @GeneratedValue
	private Long id;
	
	@NotNull(message = "La cantidad es obligatoria.")
	@Min(value = 1, message = "La cantidad debe ser al menos 1.")
	private int cantidad;
	
	private double precioUnitario;
	private double subtotal; //Precio unitario * cantidad

	@ManyToOne
	@JoinColumn (name= "pedido_codigo")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn (name= "producto_id")
	private Producto producto;
}
