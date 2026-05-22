package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
}
