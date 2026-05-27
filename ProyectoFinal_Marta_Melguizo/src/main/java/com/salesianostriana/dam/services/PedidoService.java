package com.salesianostriana.dam.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.excepciones.PedidoYaFinalizadoException;
import com.salesianostriana.dam.excepciones.StockInsuficienteException;
import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;
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
	
	//La logica de negocios
	public Map<EstadoPedido, List<Pedido>> agrupadosPorEstado() {
		return findAll().stream()
				.collect(Collectors.groupingBy(Pedido::getEstadoPedido));
	}
	
	public double totalFacturado() {
		return findAll().stream()
				.filter(p -> p.getEstadoPedido() == EstadoPedido.ENTREGADO)
				.mapToDouble(Pedido::getTotal)
				.sum();
	}

	//La gestión de las lineas de pedido
	// - Recalcular el total
	public void recalcularTotal(Pedido p) {
		double total = p.getLineas().stream()
				.mapToDouble(LineaPedido::getSubtotal)
				.sum();
		p.setTotal(total);
	}
	
	public Pedido agregarLineaPedido(Long pedidoId, Long productoId, int cantidad, ProductoService productoService) {
		double precioFinal;
		
		Pedido pedido = findById(pedidoId)
				.orElseThrow(() -> new NoSuchElementException("Pedido no encontrado: " + pedidoId));
		
		// - Cuando el pedido ya esta terminado
		if(pedido.getEstadoPedido() == EstadoPedido.ENVIADO || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
			throw new PedidoYaFinalizadoException(pedidoId);
		}
		
		Producto producto = productoService.findById(productoId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Producto no encontrado: " + productoId));
			
		if(producto.getStock() < cantidad) {
			throw new StockInsuficienteException(producto.getNombre(), producto.getStock(), cantidad);
		}
		
		// - Calculo del precio con el descuento(Tenia descuento si era socio)
		boolean esSocio = pedido.getCliente() != null && pedido.getCliente().isSocioTienda();
			precioFinal = producto.getPrecioConDescuento(esSocio);
			
		// - Crear la linea del pedido
		LineaPedido linea = new LineaPedido();
		linea.setCantidad(cantidad);
		linea.setPrecioUnitario(precioFinal);
		linea.setSubtotal(precioFinal * cantidad);
		pedido.addLinea(linea);
		
		// - Descontar el stock
		productoService.descontarStock(producto, cantidad);
		
		// - Recalcular el total
		recalcularTotal(pedido);
		
		return edit(pedido);
		
		
	
	}
}
