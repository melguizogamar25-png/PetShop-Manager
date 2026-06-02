package com.salesianostriana.dam.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Producto {

	@Id @GeneratedValue
	private Long id;
	
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
	private String nombre;
	
	@NotNull(message = "El precio es obligatorio")
	@DecimalMin(value = "0.01", message = "EL precio debe ser mayor que 0.")
	private double precio;
	
	@NotNull(message = "El stock es obligatorio")
	@Min(value = 0, message = "El stock no puede ser negativo.")
	private int stock;
	
	@Future(message = "La fecha de caducidad debe ser una fecha futura.")
	private LocalDate fechaCaducidad;
	
	private boolean devolucion;
	
	@NotNull(message = "Debes seleccionar el tipo de mascota.")
	@Enumerated(EnumType.STRING)
	//Sirve para guardar el enum como String
	private TipoMascota tipoMascota;
	
	private String imagen;
	
	@Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
	private String descripcion;
	
	//Metodo para el precio con descuento si es socio
	public double getPrecioConDescuento (boolean esSocio) {
		double dto = switch(tipoMascota) {
		case AVE, REPTIL -> 0.10;
		case ROEDOR, PEZ -> 0.08;
		default -> 0.05;
		};
		if (esSocio) {
			dto += 0.5;
		}
		return precio * (1-dto);
	}
}
