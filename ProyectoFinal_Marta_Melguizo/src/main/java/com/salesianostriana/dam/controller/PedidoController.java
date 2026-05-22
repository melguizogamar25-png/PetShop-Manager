package com.salesianostriana.dam.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.services.ClienteService;
import com.salesianostriana.dam.services.PedidoService;
import com.salesianostriana.dam.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

	private final PedidoService pedidoService;
	private final ClienteService clienteService;
	private final ProductoService productoService;
	
	@GetMapping("/")
	public String listAll(Model model) {
		model.addAttribute("pedidos", pedidoService.findAll());
		return "pedido-list";
	}
	
	//Ver los detalles
	@GetMapping("/{id}")
	public String detalles(@PathVariable long id, Model model) {
		Optional <Pedido> peDetalle = pedidoService.findById(id);
		
		if(peDetalle.isPresent()) {
			model.addAttribute("pedido", peDetalle.get());
			model.addAttribute("produstos", productoService.findAll());
			return "pedido-detail";
		}else {
			return "redirect:/pedidos/";
		}
		
	}
}
