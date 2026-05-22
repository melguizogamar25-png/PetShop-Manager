package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.services.ClienteService;

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
	
}
