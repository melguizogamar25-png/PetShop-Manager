package com.salesianostriana.dam.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.services.ClienteService;
import com.salesianostriana.dam.services.PedidoService;
import com.salesianostriana.dam.services.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {
 
	private final PedidoService   pedidoService;
	private final ClienteService  clienteService;
	private final ProductoService productoService;
 
	@GetMapping("/")
	public String listAll(Model model) {
		model.addAttribute("pedidos", pedidoService.findAll());
		model.addAttribute("totalFacturado", pedidoService.totalFacturado());
		return "pedido-list";
	}
 
	@GetMapping("/{id}")
	public String detalles(@PathVariable long id, Model model) {
		Optional<Pedido> peDetalle = pedidoService.findById(id);
		if (peDetalle.isPresent()) {
			model.addAttribute("pedido", peDetalle.get());
			model.addAttribute("productos", productoService.findAll());
			return "pedido-detail";
		}
		return "redirect:/pedidos/";
	}
 
	@GetMapping("/nuevo")
	public String showForm(Model model) {
		Pedido p = new Pedido();
		p.setFecha(LocalDate.now());
		p.setEstadoPedido(EstadoPedido.PENDIENTE);
		model.addAttribute("pedido", p);
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("estados", EstadoPedido.values());
		return "pedido-form";
	}
 
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("pedido") Pedido p,
	                   BindingResult result,
	                   @RequestParam(required = false) Long clienteId,
	                   Model model) {
		if (clienteId == null) {
			model.addAttribute("clientes", clienteService.findAll());
			model.addAttribute("estados", EstadoPedido.values());
			model.addAttribute("errorCliente", "Debes seleccionar un cliente.");
			return "pedido-form";
		}
		if (result.hasErrors()) {
			model.addAttribute("clientes", clienteService.findAll());
			model.addAttribute("estados", EstadoPedido.values());
			return "pedido-form";
		}
		clienteService.findById(clienteId).ifPresent(p::setCliente);
		p.setTotal(0.0);
		pedidoService.save(p);
		return "redirect:/pedidos/";
	}
 
	@GetMapping("/editar/{id}")
	public String showEdit(@PathVariable Long id, Model model) {
		Pedido p = pedidoService.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Pedido no encontrado."));
		model.addAttribute("pedido", p);
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("estados", EstadoPedido.values());
		return "pedido-form";
	}
 
	@PostMapping("/editar/{id}")
	public String update(@PathVariable long id,
	                     @Valid @ModelAttribute("pedido") Pedido p,
	                     BindingResult result,
	                     @RequestParam(required = false) Long clienteId,
	                     Model model) {
		if (clienteId == null) {
			model.addAttribute("clientes", clienteService.findAll());
			model.addAttribute("estados", EstadoPedido.values());
			model.addAttribute("errorCliente", "Debes seleccionar un cliente.");
			return "pedido-form";
		}
		if (result.hasErrors()) {
			model.addAttribute("clientes", clienteService.findAll());
			model.addAttribute("estados", EstadoPedido.values());
			return "pedido-form";
		}
		clienteService.findById(clienteId).ifPresent(p::setCliente);
		pedidoService.findById(id).ifPresent(existing -> {
			p.setLineas(existing.getLineas());
			pedidoService.recalcularTotal(p);
		});
		pedidoService.edit(p);
		return "redirect:/pedidos/";
	}
 
	@GetMapping("/borrar/{id}")
	public String delete(@PathVariable long id) {
		pedidoService.deleteById(id);
		return "redirect:/pedidos/";
	}
 
}
 