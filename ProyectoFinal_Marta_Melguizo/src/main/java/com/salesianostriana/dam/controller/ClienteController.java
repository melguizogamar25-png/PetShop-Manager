package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class ClienteController {

	@GetMapping ("/clientes")
	public String controladorClientes (Model model) {
		
		return "index2";
		
	}
}
