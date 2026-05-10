package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
public class ProductoController {

	private ProductoService productoService;
	
	@GetMapping ("/productos")
	public String mostrarInicio (Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "index";
		
	}
}
