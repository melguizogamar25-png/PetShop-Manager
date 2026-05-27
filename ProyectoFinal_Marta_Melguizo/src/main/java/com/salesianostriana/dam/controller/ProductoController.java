package com.salesianostriana.dam.controller;

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

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.services.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

	private final ProductoService productoService;

	@GetMapping("/")
	public String listAll(Model model,
					@RequestParam(required = false) String buscar,
					@RequestParam(required = false) String tipo) {
		
		if(buscar != null && !buscar.isBlank()) {
			model.addAttribute("productos", productoService.buscarPorNombre(buscar));
			model.addAttribute("buscar", buscar);
		}else if(tipo != null && !tipo.isBlank()) {
			model.addAttribute("productos", productoService.porTipoMascota(tipo));
			model.addAttribute("tipoSeleccionado", tipo);
		}else {
			model.addAttribute("productos", productoService.findAll());
		}
		model.addAttribute("tipos", TipoMascota.values());
		//AlertaStock
		return "producto-list";
	}
	
	@GetMapping("/nuevo")
	public String showForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("tipos", TipoMascota.values());
        return "producto-form";
    }
	
	@PostMapping("/save")
    public String save(@Valid@ModelAttribute("producto") Producto p,
    		BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("tipos", TipoMascota.values());
			return "producto-form";
		}
		productoService.save(p);
        return "redirect:/productos/";
    }
	
	//Formulario editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable long id, Model model) {
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
	public String update(@PathVariable long id, @Valid @ModelAttribute("producto") 
						Producto p, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("tipos", TipoMascota.values());
			return "producto-form";
		}
		productoService.edit(p);
		return "redirect:/productos/";
	}
	
	//Borrar
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable long id) {
		Optional <Producto> pBorrar = productoService.findById(id);
		
		if(pBorrar.isPresent()) {
			productoService.delete(pBorrar.get());
		}
		return "redirect:/productos/";
	}
	
}
