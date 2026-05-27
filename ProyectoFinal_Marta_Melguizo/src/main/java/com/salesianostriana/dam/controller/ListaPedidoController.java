package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.excepciones.PedidoYaFinalizadoException;
import com.salesianostriana.dam.excepciones.StockInsuficienteException;
import com.salesianostriana.dam.services.LineaPedidoService;
import com.salesianostriana.dam.services.PedidoService;
import com.salesianostriana.dam.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedidos/{pedidoId}/lineas")
public class ListaPedidoController {

	private final PedidoService pedidoService;
	private final ProductoService productoService;
	private final LineaPedidoService lineaPedidoService;
	

	//Añadir linea
	@PostMapping("/add")
	public String agregarLinea(@PathVariable Long pedidoId, @RequestParam Long productoId,
								@RequestParam int cantidad, RedirectAttributes ra) {
		try {
			pedidoService.agregarLineaPedido(pedidoId, productoId, cantidad, productoService);
			ra.addFlashAttribute("mensaje", "Producto añadido correctamente al pedido");
		}catch (StockInsuficienteException | PedidoYaFinalizadoException ex) {
			ra.addFlashAttribute("mensajeError", ex.getMessage());
		}
		return "redirect:/pedidos/" + pedidoId;
	}
	
	
	
}
