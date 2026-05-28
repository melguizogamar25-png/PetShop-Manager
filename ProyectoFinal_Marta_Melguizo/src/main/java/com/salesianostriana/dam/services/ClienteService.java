package com.salesianostriana.dam.services;

import java.util.Comparator;
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
	
	//Consultas derivadas
	// - Busca los clientes que el nombre contiene el texto
	public List<Cliente> buscarPorNombre(String nombre) {
		return repository.findByNombreContainingIgnoreCase(nombre);
	}
	
	// - Solo los clientes que son socios
	public List<Cliente> obtenerSocios() {
		return repository.findBySocioTiendaTrue();
	}
	
	
	
	//La logica de negocio
	// - Cuenta cuantos socios hay
	public long totalSOcios() {
		return findAll().stream()
				.filter(Cliente::isSocioTienda)
				.count();
	}
	
	/* - Gasto total del cliente y suma 
	tambien el total de todos los pedidos*/
	public double gastoTotalCliente(long clienteId) {
		return findById(clienteId)
				.map(c -> c.getPedidos().stream()
						.mapToDouble(p -> p.getTotal())
						.sum())
				.orElse(0.0);
				
	}
	
	// - Una lista de los clientes que mas han gastado
	  public List<Cliente> clientesPorGastoStream() {
	        return findAll().stream()
	                .sorted(Comparator.comparingDouble(
	                        (Cliente c) -> c.getPedidos().stream()
	                                .mapToDouble(p -> p.getTotal()).sum()
	                ).reversed())
	                .toList();
	}
}
