package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class PedidoController {

	@GetMapping ("/pedidos")
	public String controladorPedido (Model model) {
		return "pedido-list";
	}
}
