package com.salesianostriana.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	

	private ProductoService productosService;
	
	@GetMapping("/")
	public String listAll(Model model) {
		model.addAttribute("productos", productosService.findAll());
		model.addAttribute("tipos", TipoMascota.values());
		return "producto-list";
	}
	
	@GetMapping("/nuevo")
	public String showForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("tipos", TipoMascota.values());
        return "producto-form";
    }
	
	@PostMapping("/save")
    public String save(@ModelAttribute("producto") Producto p) {
		productosService.save(p);
        return "redirect:/productos/";
    }
	
	//Formulario editar
	@GetMapping("/editar/{id}")
	public String showEditrForm (@PathVariable Long id, Model model) {
		Producto p = productosService.findById(id)
				.orElseThrow();
				model.addAttribute("productos", p);
				model.addAttribute("tipos", TipoMascota.values());
				return "producto-form";
	}
	
	//Actualizar
	
	//Borrar
}
