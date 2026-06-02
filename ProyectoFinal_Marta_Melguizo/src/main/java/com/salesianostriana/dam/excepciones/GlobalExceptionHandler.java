package com.salesianostriana.dam.excepciones;

import java.util.NoSuchElementException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StockInsuficienteException.class)
	public String handleStockInsuficiente(StockInsuficienteException ex, Model model) {
		model.addAttribute("errorTitulo", "Stock Insuficiente");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(TipoMascotaInvalidoException.class)
	public String handlerTipoInvalido(TipoMascotaInvalidoException ex, Model model) {
		model.addAttribute("errorTitulo", "Tipo de Mascota Inválido");
		model.addAttribute("erroMensaje", ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(PedidoYaFinalizadoException.class)
	public String handlePedidoFinalizado(PedidoYaFinalizadoException ex, Model model) {
		model.addAttribute("errorTitulo", "Pedido FInalizado");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public String handleNotFound(Model model) {
		model.addAttribute("errorTitulo", "Elemento No Encontrado");
		model.addAttribute("errorMensaje", "El recurso solicitado no existe en la base de datos");
		return "error";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handlerIllegalArgument(IllegalArgumentException ex, Model model) {
		model.addAttribute("errorTitulo", "Operación Inválida");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public String handlerIllegalState(IllegalStateException ex, Model model) {
		model.addAttribute("errorTitulo", "Operación No Permitida");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleGeneric(Exception ex, Model model) {
		model.addAttribute("errorTitulo", "Error Inerperado");
		model.addAttribute("errorMensaje", "Ha ocurrido un error inesperado. Por favor, intentalo de nuevo");
		return "error";
	}
}
