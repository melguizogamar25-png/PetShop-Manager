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

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.services.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	
	//Ponerle los filtros
	@GetMapping("/")
	public String listAll (Model model) {
		model.addAttribute("clientes", clienteService.findAll());
		return "cliente-list";
	}
	
	@GetMapping("/nuevo")
	public String showForm(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("cliente") Cliente c, 
						BindingResult result) {
		if(result.hasErrors()) {
			return "cliente-form";
		}
		clienteService.save(c);
		return "redirect:/clientes/";
	}
	
	//Editar Fomulario
	@GetMapping("/editar/{id}")
	public String mostrarFormulariodicion (@PathVariable long id, Model model) {
		Optional<Cliente> cEditar = clienteService.findById(id);
		
		if(cEditar.isPresent()) {
			model.addAttribute("cliente", cEditar.get());
			return "cliente-form";
		}else {
			return "redirect:/clientes/";
		}
	}
	
	//Actualización del Cliente
	@PostMapping("/editar/{id}")
	public String update(@PathVariable long id, @Valid @ModelAttribute("cliente")
						Cliente c, BindingResult result) {
		if(result.hasErrors()) {
			return "cliente-form";
		}
		
		clienteService.edit(c);
		return "redirect:/clientes/";
	}
	
}
