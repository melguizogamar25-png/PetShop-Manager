package com.salesianostriana.dam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;

public interface LineaPedidoRepository extends JpaRepository <LineaPedido, Long>{

	//Consultas
	// - Buscar un pedido
	List<LineaPedido> findByPedido(Pedido pedido);
	
	List<LineaPedido> findByPedidoCodigo(Long codigoPedido);
	
	List<LineaPedido> findByProducto(Producto producto);
	
	// - Numero de lineas del pedido
	long countByPedidoCodigo(Long codigoPedido);
	

	// - Producto mas pedido
	@Query("""
			select l.producto.id, sum(l.cantidad) as total
			from LineaPedido l
			group by l.producto.id
			order by total desc
			""")
	List<Object[]> findProductoMasVendidoRaw(); 
}
