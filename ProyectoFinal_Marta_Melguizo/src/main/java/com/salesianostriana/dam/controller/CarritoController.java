package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.services.CarritoService;
import com.salesianostriana.dam.services.ClienteService;
import com.salesianostriana.dam.services.PedidoService;
import com.salesianostriana.dam.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CarritoController {

	private final CarritoService carritoService;
	private final ProductoService productoService;
	private final PedidoService pedidoService;
	private final ClienteService clienteService;
	
	@GetMapping("/carrito")
	public String verCarrito(Model model) {
		model.addAttribute("productos", carritoService.getProductosInCarr());
		return "carrito";
	}
}
