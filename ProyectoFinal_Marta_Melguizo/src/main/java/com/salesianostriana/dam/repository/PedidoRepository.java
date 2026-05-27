package com.salesianostriana.dam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long>{

	//Consultas Derivadas
	// - Busca los pedidos por el rango de fechas (DESC)
	List<Pedido> findByFechaBetweenOrderByFechaDesc(LocalDate desde, LocalDate hasta);
	
	// - Busca los pedidos del cliente por el id
	List<Pedido> findByClienteIdOrderByFechaDesc(Long clienteId);

	// - Busca pedidos con un estado especifico
	List<Pedido> findByEstadoPedido(EstadoPedido estado);
	
	// - Los pedidos que se ordenan desc por el total
	List<Pedido> findTop5ByOrderByTotalDesc();
	
	// - Busca el numero de pedidos de un cliente
	long countByClienteId(Long clienteId);
	
	// - EL total de pedidos que ya estan entregados
	@Query("select coalesce(sum(p.total), 0) from Pedido p where p.estadoPedido = 'entregado' ")
	Double sumTotalFacturado();
	
	// - El pedido que tenga el total mayor que un umbral
	@Query("select p from Pedido p where p.total >= :umbral order by p.total desc")
	List<Pedido> findPedidosConTotalMayorQue(@Param("umbral") double umbral);
}
