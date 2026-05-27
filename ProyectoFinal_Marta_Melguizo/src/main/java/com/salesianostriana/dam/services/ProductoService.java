package com.salesianostriana.dam.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.excepciones.TipoMascotaInvalidoException;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.repository.ProductoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

@Service
public class ProductoService extends BaseServiceImpl<Producto, Long, ProductoRepository>{
    
    
    //Consultas Derivadas
	public List<Producto> buscarPorNombre(String termino) {
		return repository.findByNombreContainingIgnoreCase(termino);
	}
	
	// - Filtrar tipo mascota
	public List<Producto> porTipoMascota(String tipo) {
		try {
			return repository.findByTipoMascota(TipoMascota.valueOf(tipo));
		}catch (IllegalArgumentException e) {
			throw new TipoMascotaInvalidoException(tipo);
		}
	}
	
	// - Los productos que tengan bajo el stock  y esta ordenado asc
	
	public List<Producto> productosBajoStock(int umbral) {
		return repository.findByStockLessThanEqualOrderByStockAsc(umbral);
	}
	
	public List<Producto> conDevolucion() {
		return repository.findDevolucionTrue();
	}
	
	public List<Producto> masvendidosPorTipos(TipoMascota tipo) {
		return repository.findMasVendidosPorTipo(tipo);
	}
	
	//Logica de negocios
	public double precioMedioPorTipo(TipoMascota tipo) {
		return findAll().stream()
				.filter(p -> p.getTipoMascota() == tipo)
				.mapToDouble(Producto::getPrecio)
				.average() //Calcula la media
				.orElse(0.0);
	}
}
