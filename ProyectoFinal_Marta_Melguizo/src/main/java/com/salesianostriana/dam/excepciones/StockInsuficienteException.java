package com.salesianostriana.dam.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockInsuficienteException extends RuntimeException{

	public StockInsuficienteException (String nombreProducto, int disponible,
									   int solicitado) {
		super("Stock insuficiente para '" + nombreProducto + "'. "
	         + "Disponible: " + disponible + " ud(s), solicitado: " + solicitado + ".");
	}
}
