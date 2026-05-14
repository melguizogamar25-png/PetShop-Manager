package com.salesianostriana.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.services.ProductoService;
import com.salesianostriana.dam.services.base.BaseService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

	private final ProductoService productoService = new ProductoService();
	
	@GetMapping("/")
	public String listAll (Model model) {
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("tipos", TipoMascota.values());
		return "producto-list";
	}
}
