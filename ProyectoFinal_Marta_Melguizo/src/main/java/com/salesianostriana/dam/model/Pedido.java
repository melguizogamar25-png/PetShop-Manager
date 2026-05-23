package com.salesianostriana.dam.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Pedido {

	@Id
	private Long codigo;
	
	@NotNull(message = "La fecha de pedido es obligatoria.")
	@PastOrPresent(message = "La fecha no puede ser futura.")
	private LocalDate fecha;
	
	private double total;
	
	@Size(max = 300, message = "La descripción no supera los 300 caracteres.")
	private String descripcion;
	
	@NotNull(message = "El estado es obligatorio.")
	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;
	
	//Un pedido solo tiene un cliente
	@ManyToOne 
	@JoinColumn (name= "cliente_id")
	private Cliente cliente;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	//Un pedido tiene unas cuantas de lineas de pedido
	@OneToMany 
	private List <LineaPedido> lineas = new ArrayList<>(); 
}
