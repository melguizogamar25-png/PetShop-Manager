package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	

	public List<Producto> findAll() {
		return productoRepository
				.findAll();
	}
	
	public Producto findById (Long id) {
		return productoRepository
				.findById(id)
				.orElse(null);
	}
	
	public Producto save (Producto producto) {
		return productoRepository
				.save(producto);
	}
	
	public void borrarById (Long id) {
		productoRepository
			.deleteById(id);
	}
}
