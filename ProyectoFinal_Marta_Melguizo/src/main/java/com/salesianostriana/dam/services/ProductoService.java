package com.salesianostriana.dam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.repository.ProductoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
public class ProductoService extends BaseServiceImpl<Producto, Long, ProductoRepository>{

	//Producto que estan filtrados por el tipo de mascota
	public List<Producto> findByTipoMascota(TipoMascota tipo) {
		return repository.findAll().stream()
				.filter(p -> p.getTipoMascota() == tipo)
				.toList();
	}
	
}
