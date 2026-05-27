package com.salesianostriana.dam.controller;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.excepciones.PedidoYaFinalizadoException;
import com.salesianostriana.dam.excepciones.StockInsuficienteException;
import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;
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
	
	//Eliminar linea
	@GetMapping("/{lineaId}/delete")
	public String eliminarLinea(@PathVariable Long pedidoId, @PathVariable Long lineaId,
								RedirectAttributes ra) {
		Pedido pedido = pedidoService.findById(pedidoId)
				.orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
		
		// - No se puede modificar un pedido que ya etsa finalizado
		if(pedido.getEstadoPedido() == EstadoPedido.ENVIADO || 
				pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
			throw new PedidoYaFinalizadoException(pedidoId);
		}
		
		LineaPedido linea = lineaPedidoService.findById(lineaId)
				.orElseThrow(() -> new NoSuchElementException("Línea no encontrada"));
		
		// - Devolver el stock al producto
		Producto producto = linea.getProducto();
		if(producto != null) {
			productoService.devolverStock(producto, linea.getCantidad());
		}
		
		// - Quitar la linea del pedido y recalcular el total
		pedido.removeLinea(linea);
		lineaPedidoService.delete(linea);
		pedidoService.recalcularTotal(pedido);
		pedidoService.edit(pedido);
		
		ra.addFlashAttribute("mensaje", "Línea eliminada y stock devuelto");
		return "redirect:/pedidos/" + pedidoId;
	}
	
}
