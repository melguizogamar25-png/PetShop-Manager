package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.repository.PedidoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

@Service
public class PedidoService extends BaseServiceImpl<Pedido, Long, PedidoRepository>{

	//Recalcular el total
	public void recalcularTotal(Pedido p) {
		double total = p.getLineas().stream()
				.mapToDouble(LineaPedido::getSubtotal)
				.sum();
		p.setTotal(total);
	}
	
}
