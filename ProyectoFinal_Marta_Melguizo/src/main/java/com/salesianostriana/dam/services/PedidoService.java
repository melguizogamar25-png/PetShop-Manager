package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll () {
		return pedidoRepository
				.findAll();
		
	}
	
	public Pedido findById (String codigo) {
		return pedidoRepository
				.findById(codigo)
				.orElse(null);
	}
	
	public Pedido save (Pedido p) {
		return pedidoRepository
				.save(p);
	}
}
