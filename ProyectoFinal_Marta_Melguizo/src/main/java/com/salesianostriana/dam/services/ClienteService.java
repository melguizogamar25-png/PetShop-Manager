package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll () {
		return clienteRepository
				.findAll();
	}
	
	public Cliente save(Cliente c) {
		return clienteRepository
				.save(c);
	}

}
