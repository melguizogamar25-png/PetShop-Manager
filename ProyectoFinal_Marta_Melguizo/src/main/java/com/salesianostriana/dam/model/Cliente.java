package com.salesianostriana.dam.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Cliente {

	@Id @GeneratedValue
	private Long id;
	
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(min = 2, max = 100, message = "EL nombre debe tener entre 2 y 100 caracteres.")
	private String nombre;
	
	@NotBlank(message = "El email es obligatorio.")
	@Email(message = "Introduce un email con formato válido.")
	private String email;
	
	@NotNull(message = "El teléfono es obligatorio.")
	@Min(value = 100000000, message = "El teléfono debe tener 9 dígitos.")
	@Max(value = 999999999, message = "El teléfono debe tener 9 dígitos.")
	private int telefono;
	
	private boolean socioTienda;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	//Un cliente puede tener muchos pedidos
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<Pedido> pedidos = new ArrayList<>();
	
	//Método bidireccional
	public void addPedido(Pedido p)  {
		pedidos.add(p);
		p.setCliente(this);
	}
	
	public void removePedido(Pedido p) {
		pedidos.add(p);
		p.setCliente(null);
	}
}
