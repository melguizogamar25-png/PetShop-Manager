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

	//Productos que se filtran por el nombre
	public List<Producto> findByNombre(String nombre) {
		return findAll().stream()
				.filter(p -> p.getNombre().toLowerCase().contains(nombre))
				.toList();
				
	}
	
	//Producto que estan filtrados por el tipo de mascota
    public List<Producto> porTipoMascota(TipoMascota tipo) {
        return findAll().stream()
                .filter(p -> p.getTipoMascota() == (tipo))
                .toList();
    }
    
    //CONTROL STOCK
    
	
}
