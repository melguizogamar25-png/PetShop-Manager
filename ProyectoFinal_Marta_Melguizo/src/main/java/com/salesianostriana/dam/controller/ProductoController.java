package com.salesianostriana.dam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.services.ProductoService;
import com.salesianostriana.dam.services.base.BaseService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequestMapping("/productos")
public class ProductoController {

	private ProductoService productoService;
	

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping("/")
	public String listAll(Model model) {
		model.addAttribute("productos", productoService.findAll());
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
		productoService.save(p);
        return "redirect:/productos/";
    }
	
	//Formulario editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		Optional<Producto> pEditar = productoService.findById(id);
 
		if (pEditar.isPresent()) {
			model.addAttribute("producto", pEditar.get());
			model.addAttribute("tipos", TipoMascota.values());
			return "producto-form";
		} else {
			return "redirect:/productos/";
		}
	}
	
	//Actualizar
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("producto") Producto p) {
		productoService.edit(p);
		return "redirect:/productos/";
	}
	
	//Borrar
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") Long id) {
		Optional <Producto> pBorrar = productoService.findById(id);
		
		if(pBorrar.isPresent()) {
			productoService.delete(pBorrar.get());
		}
		return "redirect:/productos/";
	}
	
	//Filtrar
	@GetMapping("/productos/filtrar")
	public String filtrar (@RequestParam(required = false) TipoMascota tipo, Model model) {
		
		if(tipo == null) {
			return "redirect:/productos/";
		}
		model.addAttribute("productos", productoService);
	}
}
