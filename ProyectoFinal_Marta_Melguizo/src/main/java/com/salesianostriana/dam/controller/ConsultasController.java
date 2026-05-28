package com.salesianostriana.dam.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.excepciones.TipoMascotaInvalidoException;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.services.ClienteService;
import com.salesianostriana.dam.services.PedidoService;
import com.salesianostriana.dam.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultasController {

	private final ProductoService productoService;
	private final PedidoService pedidoService;
	private final ClienteService clienteService;
	
	//Panel principal
	@GetMapping("/")
	public String panel(Model model) {
		model.addAttribute("tipos", TipoMascota.values());
		model.addAttribute("hoy", LocalDate.now());
		model.addAttribute("hace", LocalDate.now().minusDays(30));
		model.addAttribute("clienteGasto", clienteService.clientesPorgastosDesc());
		model.addAttribute("productoBajoStock", productoService.productosBajoStock(5));
		model.addAttribute("totalfacturado", pedidoService.totalFacturado());
		model.addAttribute("top5Pedidos", pedidoService.top5PorTotal());
		return "consultas";
	}
	
	// - Mas vendidos por su tipo de mascota
	@GetMapping("/mas-vendidos")
	public String masVendidosPorTipo(@RequestParam String tipo, Model model) {
		try {
			TipoMascota tipoM = TipoMascota.valueOf(tipo);
			model.addAttribute("masVendidos", productoService.masvendidosPorTipos(tipoM));
			model.addAttribute("TipoSeleccionado", tipoM);
		}catch (IllegalArgumentException e) {
			throw new TipoMascotaInvalidoException(tipo);
		}
		cargarDatosPanel(model);
		return "consultas";
	}
	
	// - Recargar los datos del panel
	private void cargarDatosPanel(Model model) {
		model.addAttribute("tipos", TipoMascota.values());
		model.addAttribute("hoy", LocalDate.now());
		model.addAttribute("hace", LocalDate.now().minusDays(30));
		model.addAttribute("clienteGasto", clienteService.clientesPorgastosDesc());
		model.addAttribute("productoBajoStock", productoService.productosBajoStock(5));
		model.addAttribute("totalfacturado", pedidoService.totalFacturado());
		model.addAttribute("top5Pedidos", pedidoService.top5PorTotal());

	}
	
	// - Pedir por rango de fecha
	@GetMapping("/pedidos-rango")
	public String pedidosPorrango(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
							      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
							      Model model) {
		model.addAttribute("pedidosrango", pedidoService.pedidosPorRango(desde, hasta));
		model.addAttribute("desde", desde);
		model.addAttribute("hasta", hasta);
		return "consultas";
	}
	
}
