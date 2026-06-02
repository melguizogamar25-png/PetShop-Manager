package com.salesianostriana.dam.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TipoMascotaInvalidoException extends RuntimeException{

	public TipoMascotaInvalidoException(String tipoRecibido) {
		super("El tipo de mascota '" + tipoRecibido + "' no es válido. "
	         + "Valores admitidos: Perro, Gato, Ave, Reptil, Roedor, Pez.");
	}
}
