package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.repository.LineaPedidoRepository;

@Service
public class LineaPedidoService {

	@Autowired
	public LineaPedidoRepository lineaPedidoRepository;
	
	}
