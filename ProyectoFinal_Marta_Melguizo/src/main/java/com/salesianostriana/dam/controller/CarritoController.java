package com.salesianostriana.dam.controller;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.model.Producto;
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
	
	@GetMapping("/productoACarrito/{id}")
    public String addToCart(@PathVariable Long id) {
        Producto p = productoService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado: " + id));
        carritoService.addProducto(p);
        return "redirect:/carrito";
    }
	
	//Quitar una unidad del carrito
	@GetMapping("/borrarProducto/{id}")
	public String removeOne(@PathVariable Long id) {
		Producto p = productoService.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Producto no encontrado: " + id));
		carritoService.removeProducto(p);
		return "redirect:/carrito";
	}
	
	//Quitamos el producto entero
	@GetMapping("/eliminarDelCarrito/{id}")
	public String removeAll(@PathVariable Long id) {
		carritoService.eliminarProductoCompleto(id);
		return "redirect:/carrito";
	}
	
}
