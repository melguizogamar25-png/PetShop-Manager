package com.salesianostriana.dam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;

public interface LineaPedidoRepository extends JpaRepository <LineaPedido, Long>{

	//Consultas
	// - Buscar un pedido
	List<LineaPedido> findByPedido(Pedido pedido);
}
