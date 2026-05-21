package com.salesianostriana.dam.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.services.ClienteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService clienteService;
	
	public ClienteController (ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
}
