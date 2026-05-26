package com.salesianostriana.dam.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.repository.PedidoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

@Service
public class PedidoService extends BaseServiceImpl<Pedido, Long, PedidoRepository>{

	//COnsultas derivadas
	
	public List<Pedido> pedidosPorRango(LocalDate desde, LocalDate hasta) {
		return repository.findByFechaBetweenOrderByFechaDesc(desde, hasta);
	}
	
	// - Pedidos de un cliente por su id
	public List<Pedido> pedidosDeCliente(Long clienteId) {
		return repository.findByClienteIdOrderByFechaDesc(clienteId);
	}
	
	// - Los pedidos filtrados por su estado
	public List<Pedido> pedidosPorEstado(EstadoPedido estado) {
		return repository.findByEstadoPedido(estado);
	}
	
	public List<Pedido> top5PorTotal() {
		return repository.findTop5ByOrderByTotalDesc();
	}
	
	// - EL total facturado de los pedidos que ya estan entregados
	public Double toralFacturado() {
		Double result; 
		result = repository.sumTotalFacturado();
		return result != null ? result : 0.0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Recalcular el total
	public void recalcularTotal(Pedido p) {
		double total = p.getLineas().stream()
				.mapToDouble(LineaPedido::getSubtotal)
				.sum();
		p.setTotal(total);
	}
	
	
}
