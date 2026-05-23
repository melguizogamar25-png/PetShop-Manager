package com.salesianostriana.dam.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PedidoYaFinalizadoException extends RuntimeException{

	public PedidoYaFinalizadoException (Long codigoPedido) {
		super ("EL pedido #" + codigoPedido + 
				" ya ha sido finalizado y no puede modificarse.");
	}
}
