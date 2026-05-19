package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.repository.ClienteRepository;
import com.salesianostriana.dam.repository.ProductoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService extends BaseServiceImpl<Cliente, Long, ClienteRepository>{

	private final ClienteRepository clienteRepository;
	
	

}
