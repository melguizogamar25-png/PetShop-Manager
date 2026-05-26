package com.salesianostriana.dam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{

	//Consultas derivadas
	// - Busca a los nombres que tienen texto sin contar las mayusculas
	List<Cliente> findByNombreContainingIgnoreCase(String nombre);
	
	// - Solo busca los clientes que son socios
	List<Cliente> findBySocioTiendaTrue();
	
	// - No socios
	List<Cliente> findBySocioTiendaFalse();
	
	// - EL cliente ya tiene ese email
	boolean existsByEmailIgnoreCase(String email);
	
	// - Busca a los clientes que tengan aunque sea un pedido
	@Query("select c from Cliente c where size(c.pedidos) > 0")
	List<Cliente> findClientesConPedidos();
	
	// - Ordenarlos por el total
	@Query("""
			select c from Cliente c
			order by(
				select coalesce(sum(p.total), 0)
				from Pedido p where p.cliente = c) desc""")
	List<Cliente> findClientesOrdenadosPorGastoDesc();
}
